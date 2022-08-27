package com.example.demo_spring_boot_features;

import lombok.extern.slf4j.Slf4j;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MariaDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Slf4j
public abstract class AbstractTestingContainer {

    @Container
    protected static MariaDBContainer<?> mariaDBContainer = new MariaDBContainer<>("mariadb:10.7")
            .withDatabaseName("test")
            .withUrlParam("allowMultiQueries", "true")
            .withInitScript("db/person/testCase1.sql"); 

    @DynamicPropertySource
    static void overridePro(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.driver-class-name", mariaDBContainer::getDriverClassName);
        registry.add("spring.datasource.url", mariaDBContainer::getJdbcUrl);
        registry.add("spring.datasource.username", mariaDBContainer::getUsername);
        registry.add("spring.datasource.password", mariaDBContainer::getPassword);

    }


}
