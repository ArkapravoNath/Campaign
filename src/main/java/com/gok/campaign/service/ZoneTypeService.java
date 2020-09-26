package com.gok.campaign.service;

import com.gok.campaign.service.dto.ZoneTypeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.ZoneType}.
 */
public interface ZoneTypeService {

    /**
     * Save a zoneType.
     *
     * @param zoneTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ZoneTypeDTO save(ZoneTypeDTO zoneTypeDTO);

    /**
     * Get all the zoneTypes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ZoneTypeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" zoneType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ZoneTypeDTO> findOne(Long id);

    /**
     * Delete the "id" zoneType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
