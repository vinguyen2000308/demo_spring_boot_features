package com.example.demo_spring_boot_features.repo.app1;

import com.example.demo_spring_boot_features.domain.app1.model.ModelTest1;
import com.example.demo_spring_boot_features.domain.app2.model.ModelTest2;
import com.example.demo_spring_boot_features.repo.app2.ModelTest1App2Repo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;


@SpringBootTest
@ActiveProfiles("test")
class ModelTest1App1RepoTest {

    @Autowired
    private ModelTest1App2Repo modelTest1App2Repo;

    @Autowired
    private ModelTest1App1Repo modelTest1App1Repo;

    @Test
    void test1Repo() {
        List<ModelTest2> all = modelTest1App2Repo.findAll();
        all.forEach(System.out::println);
    }
    @Test
    void test2Repo() {
        List<ModelTest1> all = modelTest1App1Repo.findAll();
        all.forEach(System.out::println);
    }

}