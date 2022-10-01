package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.model.ModelTest2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@ActiveProfiles("test")
class ModelTest2RepoTest {

    @Autowired
    private ModelTest2Repo modelTest2Repo;

    @Test
    @Transactional(transactionManager = "businessDataSourceTransManager")
    void testInsert() {
        modelTest2Repo.save(ModelTest2.builder()
                .level("Industry groups")
                .description("Agriculture")
                .seriesRefSNDQ("SG01RSC04B01AA1")
                .quarter("1987Q2")
                .weight(0.0499960)
                .amount(1594.202)
                .build());
    }

}