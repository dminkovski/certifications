package com.davidminkovski.certifications.service;

import com.davidminkovski.certifications.dto.CertificationDTO;
import com.davidminkovski.certifications.dto.CertificatonResponse;

import java.util.List;

public interface CertificationService {
    CertificationDTO createCertification(CertificationDTO dto);
    CertificatonResponse getAll(int page, int size, String sortBy, String order);

    CertificationDTO getById(Long id);

    CertificationDTO update(CertificationDTO dto, Long id);

    void delete(Long id);
}
