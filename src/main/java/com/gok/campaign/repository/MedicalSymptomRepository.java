package com.gok.campaign.repository;

import com.gok.campaign.domain.MedicalSymptom;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MedicalSymptom entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MedicalSymptomRepository extends JpaRepository<MedicalSymptom, Long> {
}
