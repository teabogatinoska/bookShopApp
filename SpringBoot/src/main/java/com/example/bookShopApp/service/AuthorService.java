package com.example.bookShopApp.service;


import com.example.bookShopApp.model.Author;
import com.example.bookShopApp.model.Book;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> getAllAuthors();

    Author getAuthorById(long id);

    Optional<Author> findById(Long id);

    void saveAuthor (Author author);
}
