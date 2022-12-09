package com.davidminkovski.certifications.repository;

import com.davidminkovski.certifications.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
