package com.davidminkovski.certifications.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "certifications"
)
public class Certification {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String link;

    @OneToMany(mappedBy = "certification", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Course> courses = new HashSet<>();

}

