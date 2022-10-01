package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.model.ModelTest1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("test")
class ModelTest1RepoTest {

    @Autowired
    private ModelTest1Repo modelTest1Repo;

    @Test
    @Transactional
    void testSetup() {
        Assertions.assertNotNull(modelTest1Repo);
        ModelTest1 modelTest1 = ModelTest1.builder()
                .year(2009)
                .sector("Central government")
                .type("Total")
                .cfnTle1("Environmental protection expenditure")
                .cfnTle2("Final consumption expenditure")
                .units("Proportion")
                .magnitude("Proportion")
                .source("Environmental Accounts")
                .dataValue(1.2)
                .flag("F")
                .build();
        modelTest1Repo.save(modelTest1);
    }
}