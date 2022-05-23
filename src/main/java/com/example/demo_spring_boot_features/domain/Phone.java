package com.example.demo_spring_boot_features.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@ToString
public class Phone {

    private String countryCode;
    private String areaCode;
    private String centralOffice;
    private String lineNumber;
}
