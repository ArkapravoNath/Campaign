package com.gok.campaign.service;

import com.gok.campaign.service.dto.ServiceReqDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.gok.campaign.domain.ServiceReq}.
 */
public interface ServiceReqService {

    /**
     * Save a serviceReq.
     *
     * @param serviceReqDTO the entity to save.
     * @return the persisted entity.
     */
    ServiceReqDTO save(ServiceReqDTO serviceReqDTO);

    /**
     * Get all the serviceReqs.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ServiceReqDTO> findAll(Pageable pageable);
    /**
     * Get all the ServiceReqDTO where Essentials is {@code null}.
     *
     * @return the {@link List} of entities.
     */
    List<ServiceReqDTO> findAllWhereEssentialsIsNull();


    /**
     * Get the "id" serviceReq.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ServiceReqDTO> findOne(Long id);

    /**
     * Delete the "id" serviceReq.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
