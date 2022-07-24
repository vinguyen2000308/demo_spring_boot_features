package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepo extends JpaRepository<Person, Long> {
}
