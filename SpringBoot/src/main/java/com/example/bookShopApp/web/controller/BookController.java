package com.example.bookShopApp.web.controller;

import com.example.bookShopApp.model.Author;
import com.example.bookShopApp.model.Book;
import com.example.bookShopApp.model.Category;
import com.example.bookShopApp.service.AuthorService;
import com.example.bookShopApp.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }


    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Book> books = this.bookService.getAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("bodyContent", "books");
        return "index";
    }

    @GetMapping("/newBook")
    public String showNewBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        List<Author> authors = this.authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "new_book";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        Book book = bookService.getBookById(id);

        model.addAttribute("book", book);
        //List<Author> authors = this.authorService.getAllAuthors();
        //model.addAttribute("authors", authors);
        return "update_book";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") long id) {


        this.bookService.deleteBookById(id);
        return "redirect:/books";
    }

    @GetMapping("/markAsTaken/{id}")
    public String markBookAsTaken(@PathVariable(value = "id") long id) {

        Book book = this.bookService.markAsTaken(id);
        this.bookService.saveBook(book);
        return "redirect:/books";

    }

}
