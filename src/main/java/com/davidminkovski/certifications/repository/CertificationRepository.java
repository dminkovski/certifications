package com.davidminkovski.certifications.repository;

import com.davidminkovski.certifications.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CertificationRepository extends JpaRepository<Certification, Long> {

}
