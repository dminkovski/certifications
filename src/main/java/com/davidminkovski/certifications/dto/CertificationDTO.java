package com.davidminkovski.certifications.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.util.Set;

@Data
public class CertificationDTO {
    private Long id;
    @NotEmpty
    @Size(min=2, message="Name should have at least 2 Characters")
    private String name;
    @NotEmpty
    @URL
    private String link;
    private Set<CourseDTO> courses;
}
