package com.gok.campaign.service;

import com.gok.campaign.service.dto.EssentialsDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.Essentials}.
 */
public interface EssentialsService {

    /**
     * Save a essentials.
     *
     * @param essentialsDTO the entity to save.
     * @return the persisted entity.
     */
    EssentialsDTO save(EssentialsDTO essentialsDTO);

    /**
     * Get all the essentials.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<EssentialsDTO> findAll(Pageable pageable);


    /**
     * Get the "id" essentials.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EssentialsDTO> findOne(Long id);

    /**
     * Delete the "id" essentials.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
