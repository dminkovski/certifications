package com.davidminkovski.certifications.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table( name="courses" )
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String link;

    private String provider;

    private Float price;

    private Float rating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="certification_id", nullable = false)
    private Certification certification;
}
