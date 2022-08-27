package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
