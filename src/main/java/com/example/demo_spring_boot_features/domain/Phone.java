package com.example.demo_spring_boot_features.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
@Entity
@Table(name = "phone")
public class Phone {
    @Id
    private String phoneNumber;
    private String countryCode;
    private String areaCode;
    private String centralOffice;
    private String lineNumber;
}
