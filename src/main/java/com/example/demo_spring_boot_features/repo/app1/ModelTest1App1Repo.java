package com.example.demo_spring_boot_features.repo.app1;

import com.example.demo_spring_boot_features.domain.app1.model.ModelTest1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTest1App1Repo extends JpaRepository<ModelTest1, Long> {
}
