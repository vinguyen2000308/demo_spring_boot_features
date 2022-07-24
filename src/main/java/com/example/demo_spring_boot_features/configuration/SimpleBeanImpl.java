package com.example.demo_spring_boot_features.configuration;

import com.example.demo_spring_boot_features.component.SimpleBean;
import org.springframework.stereotype.Component;

@Component
public class SimpleBeanImpl implements SimpleBean {


    @Override
    public String toString() {
        return "SimpleBeanImpl{ code: " + hashCode() + "}";
    }

}
