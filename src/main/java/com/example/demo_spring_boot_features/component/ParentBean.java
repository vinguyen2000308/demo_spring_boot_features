package com.example.demo_spring_boot_features.component;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@ToString
@Data
public class ParentBean {
    @Value("Smith")
    protected String familyName;
    protected String surname;
    @Value("2000-08-30")
    private LocalDate birthDay;

    public ParentBean(@Value("John") String surname) {
        this.surname = surname;
    }


}
