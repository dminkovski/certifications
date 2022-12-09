package com.davidminkovski.certifications.controller;

import com.davidminkovski.certifications.dto.CourseDTO;
import com.davidminkovski.certifications.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping("/certifications/{certId}/courses")
    public CourseDTO createCourse(@PathVariable(value = "certId") Long certId, @RequestBody CourseDTO dto){
        return service.createCourse(dto, certId);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getCourses(){
        return new ResponseEntity<>(service.getCourses(), HttpStatus.OK);
    }
}
