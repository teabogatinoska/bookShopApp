package com.example.bookShopApp.service.impl;

import com.example.bookShopApp.model.Author;
import com.example.bookShopApp.repository.AuthorRepository;
import com.example.bookShopApp.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {


    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> getAllAuthors() {
        return this.authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(long id) {
        Optional<Author> optional = this.authorRepository.findById(id);
        Author author = null;

        if (optional.isPresent()) {
            author = optional.get();
        } else {
            throw new RuntimeException("No author found with id :: " + id);
        }
        return author;
    }

    @Override
    public Optional<Author> findById(Long id) {
        return this.authorRepository.findById(id);
    }

    @Override
    public void saveAuthor(Author author) {
        this.authorRepository.save(author);
    }
}
