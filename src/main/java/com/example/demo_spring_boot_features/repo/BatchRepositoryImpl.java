package com.example.demo_spring_boot_features.repo;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

@Transactional(propagation = Propagation.NEVER)
public class BatchRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BatchRepository<T, ID> {

    public BatchRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public <S extends T> void saveInBatch(Iterable<S> entitles) {
//        BatchExecutor batchExecutor = SpringContext.getBean(BatchExecutor.class);
//        batchExecutor.saveInBatch(entitles);
    }
}
