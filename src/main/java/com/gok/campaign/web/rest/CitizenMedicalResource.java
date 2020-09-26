package com.gok.campaign.web.rest;

import com.gok.campaign.service.CitizenMedicalService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.CitizenMedicalDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.CitizenMedical}.
 */
@RestController
@RequestMapping("/api")
public class CitizenMedicalResource {

    private final Logger log = LoggerFactory.getLogger(CitizenMedicalResource.class);

    private static final String ENTITY_NAME = "campaignCitizenMedical";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CitizenMedicalService citizenMedicalService;

    public CitizenMedicalResource(CitizenMedicalService citizenMedicalService) {
        this.citizenMedicalService = citizenMedicalService;
    }

    /**
     * {@code POST  /citizen-medicals} : Create a new citizenMedical.
     *
     * @param citizenMedicalDTO the citizenMedicalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new citizenMedicalDTO, or with status {@code 400 (Bad Request)} if the citizenMedical has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/citizen-medicals")
    public ResponseEntity<CitizenMedicalDTO> createCitizenMedical(@RequestBody CitizenMedicalDTO citizenMedicalDTO) throws URISyntaxException {
        log.debug("REST request to save CitizenMedical : {}", citizenMedicalDTO);
        if (citizenMedicalDTO.getId() != null) {
            throw new BadRequestAlertException("A new citizenMedical cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CitizenMedicalDTO result = citizenMedicalService.save(citizenMedicalDTO);
        return ResponseEntity.created(new URI("/api/citizen-medicals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /citizen-medicals} : Updates an existing citizenMedical.
     *
     * @param citizenMedicalDTO the citizenMedicalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated citizenMedicalDTO,
     * or with status {@code 400 (Bad Request)} if the citizenMedicalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the citizenMedicalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/citizen-medicals")
    public ResponseEntity<CitizenMedicalDTO> updateCitizenMedical(@RequestBody CitizenMedicalDTO citizenMedicalDTO) throws URISyntaxException {
        log.debug("REST request to update CitizenMedical : {}", citizenMedicalDTO);
        if (citizenMedicalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CitizenMedicalDTO result = citizenMedicalService.save(citizenMedicalDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, citizenMedicalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /citizen-medicals} : get all the citizenMedicals.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of citizenMedicals in body.
     */
    @GetMapping("/citizen-medicals")
    public ResponseEntity<List<CitizenMedicalDTO>> getAllCitizenMedicals(Pageable pageable) {
        log.debug("REST request to get a page of CitizenMedicals");
        Page<CitizenMedicalDTO> page = citizenMedicalService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /citizen-medicals/:id} : get the "id" citizenMedical.
     *
     * @param id the id of the citizenMedicalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the citizenMedicalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/citizen-medicals/{id}")
    public ResponseEntity<CitizenMedicalDTO> getCitizenMedical(@PathVariable Long id) {
        log.debug("REST request to get CitizenMedical : {}", id);
        Optional<CitizenMedicalDTO> citizenMedicalDTO = citizenMedicalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(citizenMedicalDTO);
    }

    /**
     * {@code DELETE  /citizen-medicals/:id} : delete the "id" citizenMedical.
     *
     * @param id the id of the citizenMedicalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/citizen-medicals/{id}")
    public ResponseEntity<Void> deleteCitizenMedical(@PathVariable Long id) {
        log.debug("REST request to delete CitizenMedical : {}", id);
        citizenMedicalService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
