package com.example.demo_spring_boot_features;

import com.example.demo_spring_boot_features.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootTest
class DemoSpringBootFeaturesApplicationTests {

    @Test
    void contextLoads() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(PersonService.class);
        for (String beanName : ctx.getBeanDefinitionNames()) {
            System.out.println(beanName);
        }
    }

}
