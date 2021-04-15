package com.example.bookShopApp.service.impl;

import com.example.bookShopApp.model.Book;
import com.example.bookShopApp.model.Category;
import com.example.bookShopApp.repository.BookRepository;
import com.example.bookShopApp.service.CategoriesService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoriesService {

    private final BookRepository bookRepository;

    public CategoryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Category> getAllCategories() {

        List<Book> books = bookRepository.findAll();
        List<Category> categories = new ArrayList<>();
        for(Book b : books){
            if(!categories.contains(b.getCategory())){
                categories.add(b.getCategory());
            }
        }
        return categories;
    }



}
