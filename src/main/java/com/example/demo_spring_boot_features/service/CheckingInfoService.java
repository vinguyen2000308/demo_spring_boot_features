package com.example.demo_spring_boot_features.service;

import org.springframework.stereotype.Service;

@Service
public class CheckingInfoService {


    public boolean checkingAgeService(Integer age) {
        if (age < 18)
            throw new IllegalArgumentException("Invalid Age !");
        else
            return true;
    }
}
