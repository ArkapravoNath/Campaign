package com.gok.campaign.service;

import com.gok.campaign.service.dto.QuestionSetDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.QuestionSet}.
 */
public interface QuestionSetService {

    /**
     * Save a questionSet.
     *
     * @param questionSetDTO the entity to save.
     * @return the persisted entity.
     */
    QuestionSetDTO save(QuestionSetDTO questionSetDTO);

    /**
     * Get all the questionSets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<QuestionSetDTO> findAll(Pageable pageable);

    /**
     * Get all the questionSets with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    Page<QuestionSetDTO> findAllWithEagerRelationships(Pageable pageable);


    /**
     * Get the "id" questionSet.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<QuestionSetDTO> findOne(Long id);

    /**
     * Delete the "id" questionSet.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
