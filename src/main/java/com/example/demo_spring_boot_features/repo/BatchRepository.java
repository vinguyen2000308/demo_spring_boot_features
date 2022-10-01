package com.example.demo_spring_boot_features.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
/*Why this ?*/
public interface BatchRepository<T,ID extends Serializable> extends JpaRepository<T,ID> {
    <S extends T> void saveInBatch(Iterable<S> entitles);
}
