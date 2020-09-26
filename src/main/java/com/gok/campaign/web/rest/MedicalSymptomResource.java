package com.gok.campaign.web.rest;

import com.gok.campaign.service.MedicalSymptomService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.MedicalSymptomDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.MedicalSymptom}.
 */
@RestController
@RequestMapping("/api")
public class MedicalSymptomResource {

    private final Logger log = LoggerFactory.getLogger(MedicalSymptomResource.class);

    private static final String ENTITY_NAME = "campaignMedicalSymptom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MedicalSymptomService medicalSymptomService;

    public MedicalSymptomResource(MedicalSymptomService medicalSymptomService) {
        this.medicalSymptomService = medicalSymptomService;
    }

    /**
     * {@code POST  /medical-symptoms} : Create a new medicalSymptom.
     *
     * @param medicalSymptomDTO the medicalSymptomDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new medicalSymptomDTO, or with status {@code 400 (Bad Request)} if the medicalSymptom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/medical-symptoms")
    public ResponseEntity<MedicalSymptomDTO> createMedicalSymptom(@RequestBody MedicalSymptomDTO medicalSymptomDTO) throws URISyntaxException {
        log.debug("REST request to save MedicalSymptom : {}", medicalSymptomDTO);
        if (medicalSymptomDTO.getId() != null) {
            throw new BadRequestAlertException("A new medicalSymptom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MedicalSymptomDTO result = medicalSymptomService.save(medicalSymptomDTO);
        return ResponseEntity.created(new URI("/api/medical-symptoms/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /medical-symptoms} : Updates an existing medicalSymptom.
     *
     * @param medicalSymptomDTO the medicalSymptomDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated medicalSymptomDTO,
     * or with status {@code 400 (Bad Request)} if the medicalSymptomDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the medicalSymptomDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/medical-symptoms")
    public ResponseEntity<MedicalSymptomDTO> updateMedicalSymptom(@RequestBody MedicalSymptomDTO medicalSymptomDTO) throws URISyntaxException {
        log.debug("REST request to update MedicalSymptom : {}", medicalSymptomDTO);
        if (medicalSymptomDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MedicalSymptomDTO result = medicalSymptomService.save(medicalSymptomDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, medicalSymptomDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /medical-symptoms} : get all the medicalSymptoms.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of medicalSymptoms in body.
     */
    @GetMapping("/medical-symptoms")
    public ResponseEntity<List<MedicalSymptomDTO>> getAllMedicalSymptoms(Pageable pageable) {
        log.debug("REST request to get a page of MedicalSymptoms");
        Page<MedicalSymptomDTO> page = medicalSymptomService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /medical-symptoms/:id} : get the "id" medicalSymptom.
     *
     * @param id the id of the medicalSymptomDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the medicalSymptomDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/medical-symptoms/{id}")
    public ResponseEntity<MedicalSymptomDTO> getMedicalSymptom(@PathVariable Long id) {
        log.debug("REST request to get MedicalSymptom : {}", id);
        Optional<MedicalSymptomDTO> medicalSymptomDTO = medicalSymptomService.findOne(id);
        return ResponseUtil.wrapOrNotFound(medicalSymptomDTO);
    }

    /**
     * {@code DELETE  /medical-symptoms/:id} : delete the "id" medicalSymptom.
     *
     * @param id the id of the medicalSymptomDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/medical-symptoms/{id}")
    public ResponseEntity<Void> deleteMedicalSymptom(@PathVariable Long id) {
        log.debug("REST request to delete MedicalSymptom : {}", id);
        medicalSymptomService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
