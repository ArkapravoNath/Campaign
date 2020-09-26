package com.gok.campaign.web.rest;

import com.gok.campaign.service.CampTypeService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.CampTypeDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.CampType}.
 */
@RestController
@RequestMapping("/api")
public class CampTypeResource {

    private final Logger log = LoggerFactory.getLogger(CampTypeResource.class);

    private static final String ENTITY_NAME = "campaignCampType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CampTypeService campTypeService;

    public CampTypeResource(CampTypeService campTypeService) {
        this.campTypeService = campTypeService;
    }

    /**
     * {@code POST  /camp-types} : Create a new campType.
     *
     * @param campTypeDTO the campTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new campTypeDTO, or with status {@code 400 (Bad Request)} if the campType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/camp-types")
    public ResponseEntity<CampTypeDTO> createCampType(@RequestBody CampTypeDTO campTypeDTO) throws URISyntaxException {
        log.debug("REST request to save CampType : {}", campTypeDTO);
        if (campTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new campType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CampTypeDTO result = campTypeService.save(campTypeDTO);
        return ResponseEntity.created(new URI("/api/camp-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /camp-types} : Updates an existing campType.
     *
     * @param campTypeDTO the campTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated campTypeDTO,
     * or with status {@code 400 (Bad Request)} if the campTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the campTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/camp-types")
    public ResponseEntity<CampTypeDTO> updateCampType(@RequestBody CampTypeDTO campTypeDTO) throws URISyntaxException {
        log.debug("REST request to update CampType : {}", campTypeDTO);
        if (campTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CampTypeDTO result = campTypeService.save(campTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, campTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /camp-types} : get all the campTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of campTypes in body.
     */
    @GetMapping("/camp-types")
    public ResponseEntity<List<CampTypeDTO>> getAllCampTypes(Pageable pageable) {
        log.debug("REST request to get a page of CampTypes");
        Page<CampTypeDTO> page = campTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /camp-types/:id} : get the "id" campType.
     *
     * @param id the id of the campTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the campTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/camp-types/{id}")
    public ResponseEntity<CampTypeDTO> getCampType(@PathVariable Long id) {
        log.debug("REST request to get CampType : {}", id);
        Optional<CampTypeDTO> campTypeDTO = campTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(campTypeDTO);
    }

    /**
     * {@code DELETE  /camp-types/:id} : delete the "id" campType.
     *
     * @param id the id of the campTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/camp-types/{id}")
    public ResponseEntity<Void> deleteCampType(@PathVariable Long id) {
        log.debug("REST request to delete CampType : {}", id);
        campTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
