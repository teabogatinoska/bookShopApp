package com.example.bookShopApp.service.impl;

import com.example.bookShopApp.model.Author;
import com.example.bookShopApp.model.Book;
import com.example.bookShopApp.model.Category;
import com.example.bookShopApp.model.dto.BookDto;
import com.example.bookShopApp.model.events.BookCreatedEvent;
import com.example.bookShopApp.model.exceptions.AuthorNotFoundException;
import com.example.bookShopApp.model.exceptions.BookNotFoundException;
import com.example.bookShopApp.repository.AuthorRepository;
import com.example.bookShopApp.repository.BookRepository;
import com.example.bookShopApp.service.BookService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           ApplicationEventPublisher applicationEventPublisher) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }


    @Override
    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public void saveBook(Book book) {
        this.bookRepository.save(book);
    }

    @Override
    public Book getBookById(long id) {
        Optional<Book> optional = bookRepository.findById(id);
        Book book = null;

        if (optional.isPresent()) {
            book = optional.get();
        } else {
            throw new RuntimeException("No book found with id :: " + id);
        }
        return book;
    }

    @Override
    public void deleteBookById(long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Book markAsTaken(long id) {
        Optional<Book> optional = bookRepository.findById(id);
        Book book = null;
        if (optional.isPresent()) {
            book = optional.get();
            Integer copies = book.getAvailableCopies() - 1;
            book.setAvailableCopies(copies);

        } else {
            throw new RuntimeException("No book found with id :: " + id);
        }

        return book;
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = this.authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor().getId()));
        this.bookRepository.deleteByName(bookDto.getName());
        Book book = new Book(bookDto.getName(), bookDto.getCategory(), bookDto.getAvailableCopies(), author);
        this.bookRepository.save(book);

        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> save(String name, Category category, Integer availableCopies, Long authorId) {
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));
        this.bookRepository.deleteByName(name);
        Book book = new Book(name, category, availableCopies, author);
        this.bookRepository.save(book);

        this.applicationEventPublisher.publishEvent(new BookCreatedEvent(book));
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long id, String name, Category category, Integer availableCopies, Long authorId) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        Author author = this.authorRepository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException(authorId));

        book.setName(name);
        book.setCategory(category);
        book.setAuthor(author);
        book.setAvailableCopies(availableCopies);

        return Optional.of(book);
    }

    @Override
    @Transactional
    public Optional<Book> edit(Long id, BookDto bookDto) {
        Book book = this.bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));

        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAvailableCopies(bookDto.getAvailableCopies());

        Author author = this.authorRepository.findById(bookDto.getAuthor().getId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthor().getId()));
        book.setAuthor(author);

        this.bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.bookRepository.findAll(pageable);
    }



}
