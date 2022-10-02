package com.example.demo_spring_boot_features.repo.app2;

import com.example.demo_spring_boot_features.domain.app2.model.ModelTest2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTest1App2Repo extends JpaRepository<ModelTest2, Long> {
}
