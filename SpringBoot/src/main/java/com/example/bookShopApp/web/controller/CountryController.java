package com.example.bookShopApp.web.controller;

import com.example.bookShopApp.model.Author;
import com.example.bookShopApp.model.Country;
import com.example.bookShopApp.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public String getHomePage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Country> countries = this.countryService.getAllCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("bodyContent", "countries");
        return "countries";
    }
}
