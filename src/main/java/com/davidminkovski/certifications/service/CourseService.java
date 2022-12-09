package com.davidminkovski.certifications.service;

import com.davidminkovski.certifications.dto.CertificationDTO;
import com.davidminkovski.certifications.dto.CertificatonResponse;
import com.davidminkovski.certifications.dto.CourseDTO;

import java.util.List;

public interface CourseService {

    List<CourseDTO> getCourses();
    CourseDTO createCourse(CourseDTO dto, Long certificatonId);

    CourseDTO getById(Long id);

    CourseDTO update(CourseDTO dto, Long id);

    void delete(Long id);
}
