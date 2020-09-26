package com.gok.campaign.service;

import com.gok.campaign.service.dto.DataRowDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.DataRow}.
 */
public interface DataRowService {

    /**
     * Save a dataRow.
     *
     * @param dataRowDTO the entity to save.
     * @return the persisted entity.
     */
    DataRowDTO save(DataRowDTO dataRowDTO);

    /**
     * Get all the dataRows.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DataRowDTO> findAll(Pageable pageable);


    /**
     * Get the "id" dataRow.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DataRowDTO> findOne(Long id);

    /**
     * Delete the "id" dataRow.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
