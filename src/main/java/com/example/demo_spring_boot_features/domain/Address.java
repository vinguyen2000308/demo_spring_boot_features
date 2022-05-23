package com.example.demo_spring_boot_features.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class Address {

    private Long postalCode;
    private String state;
    private String city;
    private String streetType;
    private String streetName;
    private String houseNumber;

}
