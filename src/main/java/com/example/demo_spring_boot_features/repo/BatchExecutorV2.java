package com.example.demo_spring_boot_features.repo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
@Slf4j
public class BatchExecutorV2<T> {

    private static final ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() - 1);
    private final TransactionTemplate txTemplate;
    private final EntityManager entityManager;
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    public BatchExecutorV2(TransactionTemplate txTemplate, EntityManager entityManager) {
        this.txTemplate = txTemplate;
        this.entityManager = entityManager;
    }

    public <S extends T> void saveInBatch(List<S> entities) throws InterruptedException, ExecutionException {
        txTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        final AtomicInteger count = new AtomicInteger();
        CompletableFuture[] futures = entities.stream()
                .collect(Collectors.groupingBy(c -> count.getAndIncrement() / batchSize))
                .values()
                .stream()
                .map(this::executeBatch)
                .toArray(CompletableFuture[]::new);
        CompletableFuture<Void> run = CompletableFuture.allOf(futures);
        run.get();
    }

    public <S extends T> CompletableFuture<Void> executeBatch(List<S> list) {
        return CompletableFuture.runAsync(() -> {
            txTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    for (S entity : list)
                        entityManager.persist(entity);
                }
            });
        }, executor);
    }

}
