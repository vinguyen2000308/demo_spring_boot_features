package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.model.Author;
import com.example.demo_spring_boot_features.repo.jdbc.ForkingComponent;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

@SpringBootTest
@Slf4j
class AuthorRepoTest {


    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private BatchExecutor<Author> batchExecutor;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testSetup() {

    }

    @Test
    /*T1 - 3963 ms*/
    void testJdbcBatchingForkJoin() throws IOException {
        Integer availableCore = Runtime.getRuntime().availableProcessors();
        ForkJoinPool forkJoinPool = new ForkJoinPool(availableCore);
        List<Author> authors = new ArrayList<>();
        Integer prefixId = 400;
        for (int i = 0; i < 2000; i++) {
            Long newId = Long.valueOf(prefixId + i);
            authors.add(Author.builder()
                    .name("Test Author".concat(String.valueOf(i)))
                    .gender("MALE".concat(String.valueOf(i)))
                    .id(newId)
                    .age(10 + Math.floorMod(i, 2))
                    .build());

        }
        Long startTime = System.currentTimeMillis();
        ForkingComponent forkingComponent = applicationContext.getBean(ForkingComponent.class, authors);
        forkJoinPool.invoke(forkingComponent);
        Long endTime = System.currentTimeMillis();
        log.info("Total {} ", endTime - startTime);
    }

    @Test
    void testJdbcBatching() {
        log.info(String.valueOf(Runtime.getRuntime().availableProcessors()));

    }

    @Test
    void testSetupAuthorRepo() {
        List<Author> all = authorRepo.findAll();
        Assertions.assertFalse(all.isEmpty());
    }

    @Test
    void testInsertBatch() {
        List<Author> authors = new ArrayList<>();
        Integer prefixId = 3000;
        for (int i = 0; i < 2000; i++) {
            Long newId = Long.valueOf(prefixId + i);
            authors.add(Author.builder()
                    .name("Test Author".concat(String.valueOf(i)))
                    .gender("FMALE".concat(String.valueOf(i)))
                    .id(newId)
                    .age(7 + Math.floorMod(i, 2))
                    .build());

        }
        /*What happen when I change to identity ?*/
        /*For the Hibernate IDENTITY generator (e.g., MySQL AUTO_INCREMENT and PostgreSQL (BIG)SERIAL),
         Hibernate disables JDBC batching for INSERTs only
         (as an alternative, the developer can rely on JOOQ, which supports batching in this case as well).*/

        /*T1 - 2267 ms*/
        /*Long startTime = System.currentTimeMillis();
        authorRepo.saveAll(authors);
        Long endTime = System.currentTimeMillis();
        log.info("Total {} ", endTime - startTime);*/


        // Batch insert
        /*T1 - 890 ms
        * T2 - 2835 ms 2K
        * */
        Long startTime =  System.currentTimeMillis();
        batchExecutor.saveInBatch(authors);
        Long  endTime = System.currentTimeMillis();
        log.info("Total {} ", endTime - startTime);
    }
}