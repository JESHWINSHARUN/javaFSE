package com.example.jwtauthentication.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.jwtauthentication.model.Country;

@Service
public class CountryService {

    public List<Country> getAllCountries() {

        List<Country> countries = new ArrayList<>();

        countries.add(new Country("US", "United States"));
        countries.add(new Country("DE", "Germany"));
        countries.add(new Country("IN", "India"));
        countries.add(new Country("JP", "Japan"));

        return countries;
    }
}