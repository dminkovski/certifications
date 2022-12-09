package com.davidminkovski.certifications.dto;

import com.davidminkovski.certifications.entity.Certification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CertificatonResponse {

    private List<CertificationDTO> data;
    private int page;
    private int size;
    private String order;
    private String sortBy;
    private long totalElements;
    private long totalPages;
    private boolean isLast;
}
