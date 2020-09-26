package com.gok.campaign.service;

import com.gok.campaign.service.dto.ColumnMasterDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.ColumnMaster}.
 */
public interface ColumnMasterService {

    /**
     * Save a columnMaster.
     *
     * @param columnMasterDTO the entity to save.
     * @return the persisted entity.
     */
    ColumnMasterDTO save(ColumnMasterDTO columnMasterDTO);

    /**
     * Get all the columnMasters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ColumnMasterDTO> findAll(Pageable pageable);


    /**
     * Get the "id" columnMaster.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ColumnMasterDTO> findOne(Long id);

    /**
     * Delete the "id" columnMaster.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
