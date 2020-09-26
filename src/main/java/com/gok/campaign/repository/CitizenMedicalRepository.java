package com.gok.campaign.repository;

import com.gok.campaign.domain.CitizenMedical;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the CitizenMedical entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CitizenMedicalRepository extends JpaRepository<CitizenMedical, Long> {
}
