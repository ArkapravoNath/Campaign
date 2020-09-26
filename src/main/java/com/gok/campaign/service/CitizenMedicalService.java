package com.gok.campaign.service;

import com.gok.campaign.service.dto.CitizenMedicalDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.CitizenMedical}.
 */
public interface CitizenMedicalService {

    /**
     * Save a citizenMedical.
     *
     * @param citizenMedicalDTO the entity to save.
     * @return the persisted entity.
     */
    CitizenMedicalDTO save(CitizenMedicalDTO citizenMedicalDTO);

    /**
     * Get all the citizenMedicals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CitizenMedicalDTO> findAll(Pageable pageable);


    /**
     * Get the "id" citizenMedical.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CitizenMedicalDTO> findOne(Long id);

    /**
     * Delete the "id" citizenMedical.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
