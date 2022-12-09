package com.davidminkovski.certifications.controller;

import com.davidminkovski.certifications.dto.CertificationDTO;
import com.davidminkovski.certifications.dto.CertificatonResponse;
import com.davidminkovski.certifications.entity.Certification;
import com.davidminkovski.certifications.service.CertificationService;
import com.davidminkovski.certifications.utils.AppConstants;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/certifications")
public class CertificationController {

    @Autowired
    private CertificationService service;

    @RolesAllowed("ADMIN")
    @PostMapping
    public ResponseEntity<CertificationDTO> createCertification(@Valid @RequestBody CertificationDTO dto){
        return new ResponseEntity<>(service.createCertification(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public CertificatonResponse getCertifications(
            @RequestParam(value="page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int page,
            @RequestParam(value="size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int size,
            @RequestParam(value="sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value="order", defaultValue = AppConstants.DEFAULT_SORT_ORDER, required = false) String order
    ){
        return service.getAll(page, size, sortBy, order);
    }

    @GetMapping("{id}")
    public CertificationDTO getCertification(@PathVariable Long id){
        return service.getById(id);
    }

    @PutMapping("{id}")
    public CertificationDTO updateCertificaton(@Valid @RequestBody CertificationDTO dto, @PathVariable Long id ){
        return service.update(dto, id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCertification(@PathVariable Long id){
        service.delete(id);
        return new ResponseEntity<>("Successfully deleted.", HttpStatus.OK);
    }
}
