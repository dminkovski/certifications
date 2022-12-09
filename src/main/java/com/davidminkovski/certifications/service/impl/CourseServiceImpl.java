package com.davidminkovski.certifications.service.impl;

import com.davidminkovski.certifications.dto.CertificationDTO;
import com.davidminkovski.certifications.dto.CourseDTO;
import com.davidminkovski.certifications.entity.Certification;
import com.davidminkovski.certifications.entity.Course;
import com.davidminkovski.certifications.exceptions.ResourceNotFoundException;
import com.davidminkovski.certifications.repository.CertificationRepository;
import com.davidminkovski.certifications.repository.CourseRepository;
import com.davidminkovski.certifications.service.CourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private ModelMapper mapper;
    @Autowired
    private CourseRepository repository;
    @Autowired
    private CertificationRepository certRepository;

    @Override
    public List<CourseDTO> getCourses() {
        return repository.findAll().stream().map(c -> mapToDto(c)).collect(Collectors.toList());
    }

    @Override
    public CourseDTO createCourse( CourseDTO dto, Long certificatonId) {
        Certification certificaton = certRepository.findById(certificatonId).orElseThrow(() -> new ResourceNotFoundException("Certification","id",String.format("%d", certificatonId)));
        Course course = mapToEntity(dto);
        course.setCertification(certificaton);
        Course newCourse = repository.save(course);
        return mapToDto(newCourse);
    }

    @Override
    public CourseDTO getById(Long id) {
        return mapToDto(repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course","id",String.format("%d",id))));
    }

    @Override
    public CourseDTO update(CourseDTO dto, Long id) {
        return null;
    }


    @Override
    public void delete(Long id) {

    }

    /**
     * Convert Entity to DTO
     * @param course
     * @return CertificationDTO
     */
    private CourseDTO mapToDto(Course course){
       return mapper.map(course, CourseDTO.class);
    }

    /**
     * Convert DTO to Entity
     * @param dto
     * @return Course
     */
    private Course mapToEntity(CourseDTO dto){
        return mapper.map(dto, Course.class);
    }
}
