package com.davidminkovski.certifications.service.impl;

import com.davidminkovski.certifications.dto.CertificationDTO;
import com.davidminkovski.certifications.dto.CertificatonResponse;
import com.davidminkovski.certifications.entity.Certification;
import com.davidminkovski.certifications.exceptions.ResourceNotFoundException;
import com.davidminkovski.certifications.repository.CertificationRepository;
import com.davidminkovski.certifications.service.CertificationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CertificationServiceImpl implements CertificationService {

    @Autowired
    private CertificationRepository repository;

    @Autowired
    private ModelMapper mapper;
    @Override
    public CertificationDTO createCertification(CertificationDTO dto) {
        Certification newCert = repository.save(mapToEntity(dto));
        return mapToDto(newCert);
    }

    @Override
    public CertificatonResponse getAll(int page, int size, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Certification> pages = repository.findAll(pageable);
        List<Certification> all = pages.getContent();
        List<CertificationDTO> dtos = all.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        CertificatonResponse response = new CertificatonResponse(dtos, page, size, order, sortBy, pages.getTotalElements(), pages.getTotalPages(), pages.isLast() );
        return response;
    }

    @Override
    public CertificationDTO getById(Long id) {
        return mapToDto(repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Certification","id",String.format("%d",id))));
    }

    @Override
    public CertificationDTO update(CertificationDTO dto, Long id) {
        CertificationDTO savedDTO = getById(id);
        Certification cert = mapToEntity(savedDTO);
        cert.setLink(dto.getLink());
        cert.setName(dto.getName());
        Certification updatedCert = repository.save(cert);
        return mapToDto(updatedCert);
    }

    @Override
    public void delete(Long id) {
        CertificationDTO savedDTO = getById(id);
        Certification cert = mapToEntity(savedDTO);
        repository.delete(cert);
    }

    /**
     * Convert Entity to DTO
     * @param cert
     * @return CertificationDTO
     */
    private CertificationDTO mapToDto(Certification cert){
        return mapper.map(cert, CertificationDTO.class);
    }

    /**
     * Convert DTO to Entity
     * @param dto
     * @return Certification
     */
    private Certification mapToEntity(CertificationDTO dto){
        return mapper.map(dto, Certification.class);
    }
}
