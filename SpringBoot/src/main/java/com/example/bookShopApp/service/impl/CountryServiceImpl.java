package com.example.bookShopApp.service.impl;

import com.example.bookShopApp.model.Country;
import com.example.bookShopApp.repository.CountryRepository;
import com.example.bookShopApp.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> getAllCountries() {
        return this.countryRepository.findAll();
    }
}
