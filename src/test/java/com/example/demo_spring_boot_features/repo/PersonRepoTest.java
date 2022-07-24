package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.config.TestApplicationConfiguration;
import com.example.demo_spring_boot_features.config.TestConfigRepo;
import com.example.demo_spring_boot_features.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;



@SpringJUnitConfig(classes = {
        TestApplicationConfiguration.class,
        TestConfigRepo.class
})
@Slf4j
public class PersonRepoTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    @BeforeClass
    public void setUp() {
        Assertions.assertNotNull(jdbcTemplate);
    }

    @Test
    @Transactional
    public void getDataFromPerson() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS person;");
        jdbcTemplate.execute("CREATE table person(\n" +
                "id bigint primary key, \n" +
                "name tinytext not null\n" +
                ");\n");
        jdbcTemplate.execute("insert into person values (1, \"vi\"); ");
        jdbcTemplate.execute("insert into person values (2, \"van\"); ");
        jdbcTemplate.execute("insert into person values (3, \"nguyen\"); ");


        List<Person> persons = jdbcTemplate.query(" select * from person ", (rs, rowNum) -> Person.builder()
                .id(rs.getLong("id"))
                .firstName(rs.getString("name"))
                .build());

        Assertions.assertEquals(3L, persons.size());
        log.info("===============> Size of persons {} <===============", persons.size());
        persons.forEach(System.out::println);
    }
}