package com.example.demo_spring_boot_features.domain.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "author")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Author {

    @Id
    @Column(name = "author_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "genre")
    private String gender;

    @Column(name = "age")
    private Integer age;

}

