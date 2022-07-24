package com.example.demo_spring_boot_features.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component

public class ChildBean extends ParentBean {
    private final Boolean adult;

    public ChildBean(@Value("Lil' John") String surname, @Value("false") Boolean adult) {
        super(surname);
        this.adult = adult;
    }




}
