package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.model.ModelTest2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTest2Repo extends JpaRepository<ModelTest2, Long> {
}
