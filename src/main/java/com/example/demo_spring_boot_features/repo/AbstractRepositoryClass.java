package com.example.demo_spring_boot_features.repo;

import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractRepositoryClass<T> implements JpaRepository<T, Long> {
}
