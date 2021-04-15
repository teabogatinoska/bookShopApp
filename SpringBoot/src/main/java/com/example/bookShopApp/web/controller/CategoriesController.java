package com.example.bookShopApp.web.controller;

import com.example.bookShopApp.model.Book;
import com.example.bookShopApp.model.Category;
import com.example.bookShopApp.service.AuthorService;
import com.example.bookShopApp.service.BookService;
import com.example.bookShopApp.service.CategoriesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoriesController {

    private final CategoriesService categoriesService;

    public CategoriesController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public String getAllCategories(Model model){

        List<Category> categories = categoriesService.getAllCategories();
        model.addAttribute("categories", categories);
        return "categories";
    }
}
