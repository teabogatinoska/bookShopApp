package com.example.bookShopApp.service;

import com.example.bookShopApp.model.Book;
import com.example.bookShopApp.model.Category;
import com.example.bookShopApp.model.dto.BookDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> findById(Long id);

    void saveBook(Book book);

    Book getBookById(long id);

    void deleteBookById(long id);


    Book markAsTaken(long id);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> save(String name, Category category, Integer availableCopies, Long authorId);

    Optional<Book> edit(Long id, String name, Category category, Integer availableCopies, Long authorId);

    Optional<Book> edit(Long id, BookDto bookDto);

    Page<Book> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
