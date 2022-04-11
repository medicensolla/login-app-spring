package com.medicensoya.loginproject.services;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class EmailValidatorService implements Predicate<String> {
    @Override
    public boolean test(String s) {

        //TODO: Regex to Validate Email
        return true;
    }
}
