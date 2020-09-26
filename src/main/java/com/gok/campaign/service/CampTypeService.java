package com.gok.campaign.service;

import com.gok.campaign.service.dto.CampTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.CampType}.
 */
public interface CampTypeService {

    /**
     * Save a campType.
     *
     * @param campTypeDTO the entity to save.
     * @return the persisted entity.
     */
    CampTypeDTO save(CampTypeDTO campTypeDTO);

    /**
     * Get all the campTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<CampTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" campType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CampTypeDTO> findOne(Long id);

    /**
     * Delete the "id" campType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
