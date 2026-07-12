package com.cognizant.ormlearn;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;

@SpringBootApplication
public class OrmLearnApplication {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(OrmLearnApplication.class);

    private static CountryService countryService;

    public static void main(String[] args) {

        ApplicationContext context =
                SpringApplication.run(OrmLearnApplication.class, args);

        countryService = context.getBean(CountryService.class);
        testAddCountry();
        testFindCountry();
        testGetAllCountries();

        testAddCountry();
    }
    private static void testFindCountry() {

        LOGGER.info("Start");

        try {
            Country country = countryService.findCountryByCode("IN");

            LOGGER.info("Country : {}", country);

        } catch (CountryNotFoundException e) {

            LOGGER.error(e.getMessage());

        }

        LOGGER.info("End");
    }

    private static void testGetAllCountries() {

        LOGGER.info("Start");

        List<Country> countries = countryService.getAllCountries();

        LOGGER.info("Countries : {}", countries);

        LOGGER.info("End");
    }
    private static void testAddCountry() {

        LOGGER.info("Start");

        Country country = new Country();

        country.setCode("NP");
        country.setName("New Paradise");

        countryService.addCountry(country);

        try {
            Country result = countryService.findCountryByCode("NP");
            LOGGER.info("Added Country : {}", result);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }

        LOGGER.info("End");
    }
}