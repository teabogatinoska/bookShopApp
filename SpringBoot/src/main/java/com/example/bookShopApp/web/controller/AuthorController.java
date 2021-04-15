package com.example.bookShopApp.web.controller;

import com.example.bookShopApp.model.Author;
import com.example.bookShopApp.model.Book;
import com.example.bookShopApp.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Author> authors = this.authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        model.addAttribute("bodyContent", "authors");
        return "authors";
    }


}

