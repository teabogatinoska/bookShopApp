package com.example.bookShopApp.web.rest;

import com.example.bookShopApp.model.Category;
import com.example.bookShopApp.service.CategoriesService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/categories")
public class CategoriesRestController {

    private final CategoriesService categoriesService;

    public CategoriesRestController(CategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public List<Category> getAllCategories() {

        return this.categoriesService.getAllCategories();

    }
}
