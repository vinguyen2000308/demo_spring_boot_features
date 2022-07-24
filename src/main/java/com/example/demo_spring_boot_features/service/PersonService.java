package com.example.demo_spring_boot_features.service;

import com.example.demo_spring_boot_features.domain.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public void doSomething() {
        System.out.println("Do something in here!");
    }

    public Person createPerson(String fName, String lastName, Integer age) {
        Person person = new Person(fName, lastName, age);
        System.out.println(person);
        return person;
    }
}
