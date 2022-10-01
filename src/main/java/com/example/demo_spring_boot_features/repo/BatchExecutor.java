package com.example.demo_spring_boot_features.repo;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Slf4j
public class BatchExecutor<T> {
    private final EntityManagerFactory entityManagerFactory;
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    public BatchExecutor(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public <S extends  T> void updateBatchSize(Iterable<S> entities)
    {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session unwrap = entityManager.unwrap(Session.class);
        unwrap.setJdbcBatchSize(batchSize + 10);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            int i = 0;
            for (S entity : entities) {
                if (i % unwrap.getJdbcBatchSize()  == 0 && i > 0) {
                    log.info("Flushing the EntityManager containing {} entities ...", batchSize);
                    entityTransaction.commit();
                    entityTransaction.begin();
                    entityManager.clear();
                }
                entityManager.merge(entity);
                i++;
            }
            log.info("Flushing the remaining entities ...");
            entityTransaction.commit();
        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }
    public <S extends T> void saveInBatch(Iterable<S> entities) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Session unwrap = entityManager.unwrap(Session.class);
        unwrap.setJdbcBatchSize(batchSize + 10);
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            int i = 0;
            for (S entity : entities) {
                if (i % unwrap.getJdbcBatchSize()  == 0 && i > 0) {
                    log.info("Flushing the EntityManager containing {} entities ...", batchSize);
                    entityTransaction.commit();
                    entityTransaction.begin();
                    entityManager.clear();
                }
                entityManager.persist(entity);
                i++;
            }
            log.info("Flushing the remaining entities ...");
            entityTransaction.commit();
        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            throw e;
        } finally {
            entityManager.close();
        }
    }

}
