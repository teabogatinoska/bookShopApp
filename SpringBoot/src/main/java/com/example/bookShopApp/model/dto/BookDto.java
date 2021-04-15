package com.example.bookShopApp.model.dto;


import com.example.bookShopApp.model.Author;
import com.example.bookShopApp.model.Category;
import lombok.Data;

@Data
public class BookDto {

    private String name;

    private Category category;

    private Integer availableCopies;

    private Author author;


    public BookDto(String name, Category category, Integer availableCopies, Author author) {
        this.name = name;
        this.category = category;
        this.availableCopies = availableCopies;
        this.author = author;
    }
}

