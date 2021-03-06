package com.gok.campaign.web.rest;

import com.gok.campaign.service.AdditionalSymptomsService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.AdditionalSymptomsDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.gok.campaign.domain.AdditionalSymptoms}.
 */
@RestController
@RequestMapping("/api")
public class AdditionalSymptomsResource {

    private final Logger log = LoggerFactory.getLogger(AdditionalSymptomsResource.class);

    private static final String ENTITY_NAME = "campaignAdditionalSymptoms";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdditionalSymptomsService additionalSymptomsService;

    public AdditionalSymptomsResource(AdditionalSymptomsService additionalSymptomsService) {
        this.additionalSymptomsService = additionalSymptomsService;
    }

    /**
     * {@code POST  /additional-symptoms} : Create a new additionalSymptoms.
     *
     * @param additionalSymptomsDTO the additionalSymptomsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new additionalSymptomsDTO, or with status {@code 400 (Bad Request)} if the additionalSymptoms has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/additional-symptoms")
    public ResponseEntity<AdditionalSymptomsDTO> createAdditionalSymptoms(@RequestBody AdditionalSymptomsDTO additionalSymptomsDTO) throws URISyntaxException {
        log.debug("REST request to save AdditionalSymptoms : {}", additionalSymptomsDTO);
        if (additionalSymptomsDTO.getId() != null) {
            throw new BadRequestAlertException("A new additionalSymptoms cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AdditionalSymptomsDTO result = additionalSymptomsService.save(additionalSymptomsDTO);
        return ResponseEntity.created(new URI("/api/additional-symptoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /additional-symptoms} : Updates an existing additionalSymptoms.
     *
     * @param additionalSymptomsDTO the additionalSymptomsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated additionalSymptomsDTO,
     * or with status {@code 400 (Bad Request)} if the additionalSymptomsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the additionalSymptomsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/additional-symptoms")
    public ResponseEntity<AdditionalSymptomsDTO> updateAdditionalSymptoms(@RequestBody AdditionalSymptomsDTO additionalSymptomsDTO) throws URISyntaxException {
        log.debug("REST request to update AdditionalSymptoms : {}", additionalSymptomsDTO);
        if (additionalSymptomsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AdditionalSymptomsDTO result = additionalSymptomsService.save(additionalSymptomsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, additionalSymptomsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /additional-symptoms} : get all the additionalSymptoms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of additionalSymptoms in body.
     */
    @GetMapping("/additional-symptoms")
    public ResponseEntity<List<AdditionalSymptomsDTO>> getAllAdditionalSymptoms(Pageable pageable) {
        log.debug("REST request to get a page of AdditionalSymptoms");
        Page<AdditionalSymptomsDTO> page = additionalSymptomsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /additional-symptoms/:id} : get the "id" additionalSymptoms.
     *
     * @param id the id of the additionalSymptomsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the additionalSymptomsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/additional-symptoms/{id}")
    public ResponseEntity<AdditionalSymptomsDTO> getAdditionalSymptoms(@PathVariable Long id) {
        log.debug("REST request to get AdditionalSymptoms : {}", id);
        Optional<AdditionalSymptomsDTO> additionalSymptomsDTO = additionalSymptomsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(additionalSymptomsDTO);
    }

    /**
     * {@code DELETE  /additional-symptoms/:id} : delete the "id" additionalSymptoms.
     *
     * @param id the id of the additionalSymptomsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/additional-symptoms/{id}")
    public ResponseEntity<Void> deleteAdditionalSymptoms(@PathVariable Long id) {
        log.debug("REST request to delete AdditionalSymptoms : {}", id);
        additionalSymptomsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
