package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.model.ModelTest1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTest1Repo extends JpaRepository<ModelTest1, Long> {
}
