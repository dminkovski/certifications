package com.davidminkovski.certifications.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
public class CourseDTO {
    private Long id;
    @NotEmpty
    @Size(min=2)
    private String name;
    @NotEmpty
    @URL
    private String link;
    @NotEmpty
    @Size(min=2)
    private String provider;
    private Float price;
    private Float rating;
}
