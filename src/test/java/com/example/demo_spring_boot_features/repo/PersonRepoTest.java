package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.AbstractTestingContainer;
import com.example.demo_spring_boot_features.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class PersonRepoTest extends AbstractTestingContainer {

    @Autowired
    private PersonRepo personRepo;


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void testJdbcTemplate() {
        Assertions.assertNotNull(jdbcTemplate);
        Assertions.assertNotNull(entityManager);
    }

    @Test
    public void testPersonRepo() {

        Assertions.assertNotNull(personRepo);
        List<Person> all = personRepo.findAll();
        Assertions.assertEquals(1L, all.size());
        for (Person person : all) {
            log.info(person.toString());
        }
    }

}
