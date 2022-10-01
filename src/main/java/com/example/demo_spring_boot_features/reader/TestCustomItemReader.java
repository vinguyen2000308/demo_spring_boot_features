package com.example.demo_spring_boot_features.reader;


import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.io.Serializable;

public class TestCustomItemReader implements ItemReader {

    @Override
    public Object read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
