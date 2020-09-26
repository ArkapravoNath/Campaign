package com.gok.campaign.service;

import com.gok.campaign.service.dto.DataColumnDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.DataColumn}.
 */
public interface DataColumnService {

    /**
     * Save a dataColumn.
     *
     * @param dataColumnDTO the entity to save.
     * @return the persisted entity.
     */
    DataColumnDTO save(DataColumnDTO dataColumnDTO);

    /**
     * Get all the dataColumns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DataColumnDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dataColumn.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataColumnDTO> findOne(Long id);

    /**
     * Delete the "id" dataColumn.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
