package com.gok.campaign.service;

import com.gok.campaign.service.dto.AnswerSetDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.AnswerSet}.
 */
public interface AnswerSetService {

    /**
     * Save a answerSet.
     *
     * @param answerSetDTO the entity to save.
     * @return the persisted entity.
     */
    AnswerSetDTO save(AnswerSetDTO answerSetDTO);

    /**
     * Get all the answerSets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AnswerSetDTO> findAll(Pageable pageable);


    /**
     * Get the "id" answerSet.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AnswerSetDTO> findOne(Long id);

    /**
     * Delete the "id" answerSet.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
