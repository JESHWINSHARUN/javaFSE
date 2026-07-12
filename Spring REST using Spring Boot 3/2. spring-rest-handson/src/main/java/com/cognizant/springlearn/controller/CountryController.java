package com.cognizant.springlearn.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.model.Country;

@RestController
public class CountryController {

    @GetMapping("/country")
    public Country getCountryIndia() {

        return new Country("IN", "India");
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {

        List<Country> list = new ArrayList<>();

        list.add(new Country("IN", "India"));
        list.add(new Country("US", "United States"));
        list.add(new Country("JP", "Japan"));
        list.add(new Country("DE", "Germany"));

        return list;
    }

    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {

        List<Country> list = new ArrayList<>();

        list.add(new Country("IN", "India"));
        list.add(new Country("US", "United States"));
        list.add(new Country("JP", "Japan"));
        list.add(new Country("DE", "Germany"));

        for (Country c : list) {
            if (c.getCode().equalsIgnoreCase(code)) {
                return c;
            }
        }

        return null;
    }
}