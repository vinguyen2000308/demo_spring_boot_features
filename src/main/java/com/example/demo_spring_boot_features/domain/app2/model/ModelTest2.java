package com.example.demo_spring_boot_features.domain.app2.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(catalog = "test_database", name = "test1")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ModelTest2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "year")
    private Integer year;
    @Column(name = "sector")
    private String sector;
    @Column(name = "type")
    private String type;
    @Column(name = "cfn_tle1")
    private String cfnTle1;
    @Column(name = "cfn_tle2")
    private String cfnTle2;
    @Column(name = "units")
    private String units;
    @Column(name = "magnitude")
    private String magnitude;
    @Column(name = "source")
    private String source;
    @Column(name = "data_value")
    private Double dataValue;
    @Column(name = "flag")
    private String flag;
}


