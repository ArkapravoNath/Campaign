package com.gok.campaign.service;

import com.gok.campaign.service.dto.AdditionalSymptomsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.AdditionalSymptoms}.
 */
public interface AdditionalSymptomsService {

    /**
     * Save a additionalSymptoms.
     *
     * @param additionalSymptomsDTO the entity to save.
     * @return the persisted entity.
     */
    AdditionalSymptomsDTO save(AdditionalSymptomsDTO additionalSymptomsDTO);

    /**
     * Get all the additionalSymptoms.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AdditionalSymptomsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" additionalSymptoms.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AdditionalSymptomsDTO> findOne(Long id);

    /**
     * Delete the "id" additionalSymptoms.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
