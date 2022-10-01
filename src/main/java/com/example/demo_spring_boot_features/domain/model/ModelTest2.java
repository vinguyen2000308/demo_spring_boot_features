package com.example.demo_spring_boot_features.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(catalog = "test_database", name = "test2")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ModelTest2 {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hilo")
    @GenericGenerator(name = "hilo", strategy =
            "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "hilo_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "100"),
                    @org.hibernate.annotations.Parameter(name = "optimizer", value = "hilo")
            }
    )
    private Long id;
    @Column(name = "level")
    private String level;
    @Column(name = "description")
    private String description;
    @Column(name = "series_ref_sndq")
    private String seriesRefSNDQ;
    @Column(name = "quarter")
    private String quarter;
    @Column(name = "weight")
    private Double weight;
    @Column(name = "amount")
    private Double amount;

}



