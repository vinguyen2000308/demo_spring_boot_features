package com.example.demo_spring_boot_features.aspect;

import com.example.demo_spring_boot_features.service.CheckingInfoService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PersonServiceAspect {

    @Autowired
    private CheckingInfoService checkingInfoService;

    @Before(value = "execution(* com.example.demo_spring_boot_features.service.PersonService.doSomething())")
    public void beforeDoSomething() {
        System.out.println("Before call do something!");
    }

    @Before(value = "execution(* com.example.demo_spring_boot_features.service.PersonService.createPerson(..)) && args(fName, lastName, age)")
    public void beforeCreatePerson(String fName, String lastName, Integer age) {
        checkingInfoService.checkingAgeService(age);
        System.out.println("Create a person with info: " + fName + " " + lastName + " " + age);

    }
}
