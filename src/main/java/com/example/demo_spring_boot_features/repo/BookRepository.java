package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
