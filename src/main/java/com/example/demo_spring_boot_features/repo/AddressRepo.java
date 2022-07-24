package com.example.demo_spring_boot_features.repo;

import com.example.demo_spring_boot_features.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepo extends JpaRepository<Address,Long> {
}
