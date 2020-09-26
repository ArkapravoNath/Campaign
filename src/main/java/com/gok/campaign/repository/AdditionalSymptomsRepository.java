package com.gok.campaign.repository;

import com.gok.campaign.domain.AdditionalSymptoms;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AdditionalSymptoms entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AdditionalSymptomsRepository extends JpaRepository<AdditionalSymptoms, Long> {
}
