package com.gok.campaign.service;

import com.gok.campaign.service.dto.FileParseRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.FileParseRequest}.
 */
public interface FileParseRequestService {

    /**
     * Save a fileParseRequest.
     *
     * @param fileParseRequestDTO the entity to save.
     * @return the persisted entity.
     */
    FileParseRequestDTO save(FileParseRequestDTO fileParseRequestDTO);

    /**
     * Get all the fileParseRequests.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<FileParseRequestDTO> findAll(Pageable pageable);


    /**
     * Get the "id" fileParseRequest.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FileParseRequestDTO> findOne(Long id);

    /**
     * Delete the "id" fileParseRequest.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
