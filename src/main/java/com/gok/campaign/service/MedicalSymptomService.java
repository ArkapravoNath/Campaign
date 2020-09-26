package com.gok.campaign.service;

import com.gok.campaign.service.dto.MedicalSymptomDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.MedicalSymptom}.
 */
public interface MedicalSymptomService {

    /**
     * Save a medicalSymptom.
     *
     * @param medicalSymptomDTO the entity to save.
     * @return the persisted entity.
     */
    MedicalSymptomDTO save(MedicalSymptomDTO medicalSymptomDTO);

    /**
     * Get all the medicalSymptoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<MedicalSymptomDTO> findAll(Pageable pageable);


    /**
     * Get the "id" medicalSymptom.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MedicalSymptomDTO> findOne(Long id);

    /**
     * Delete the "id" medicalSymptom.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
