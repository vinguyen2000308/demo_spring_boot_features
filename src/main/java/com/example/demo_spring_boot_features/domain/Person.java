package com.example.demo_spring_boot_features.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class Person {

    private Long id;
    private String lastName;
    private String firstName;
    private Integer age;
    private Address address;
    private Phone phone;

    public UUID getUuid() {
        return UUID.randomUUID();
    }

}
