package com.gok.campaign.web.rest;

import com.gok.campaign.service.EssentialsService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.EssentialsDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.Essentials}.
 */
@RestController
@RequestMapping("/api")
public class EssentialsResource {

    private final Logger log = LoggerFactory.getLogger(EssentialsResource.class);

    private static final String ENTITY_NAME = "campaignEssentials";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EssentialsService essentialsService;

    public EssentialsResource(EssentialsService essentialsService) {
        this.essentialsService = essentialsService;
    }

    /**
     * {@code POST  /essentials} : Create a new essentials.
     *
     * @param essentialsDTO the essentialsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new essentialsDTO, or with status {@code 400 (Bad Request)} if the essentials has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/essentials")
    public ResponseEntity<EssentialsDTO> createEssentials(@RequestBody EssentialsDTO essentialsDTO) throws URISyntaxException {
        log.debug("REST request to save Essentials : {}", essentialsDTO);
        if (essentialsDTO.getId() != null) {
            throw new BadRequestAlertException("A new essentials cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EssentialsDTO result = essentialsService.save(essentialsDTO);
        return ResponseEntity.created(new URI("/api/essentials/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /essentials} : Updates an existing essentials.
     *
     * @param essentialsDTO the essentialsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated essentialsDTO,
     * or with status {@code 400 (Bad Request)} if the essentialsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the essentialsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/essentials")
    public ResponseEntity<EssentialsDTO> updateEssentials(@RequestBody EssentialsDTO essentialsDTO) throws URISyntaxException {
        log.debug("REST request to update Essentials : {}", essentialsDTO);
        if (essentialsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        EssentialsDTO result = essentialsService.save(essentialsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, essentialsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /essentials} : get all the essentials.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of essentials in body.
     */
    @GetMapping("/essentials")
    public ResponseEntity<List<EssentialsDTO>> getAllEssentials(Pageable pageable) {
        log.debug("REST request to get a page of Essentials");
        Page<EssentialsDTO> page = essentialsService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /essentials/:id} : get the "id" essentials.
     *
     * @param id the id of the essentialsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the essentialsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/essentials/{id}")
    public ResponseEntity<EssentialsDTO> getEssentials(@PathVariable Long id) {
        log.debug("REST request to get Essentials : {}", id);
        Optional<EssentialsDTO> essentialsDTO = essentialsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(essentialsDTO);
    }

    /**
     * {@code DELETE  /essentials/:id} : delete the "id" essentials.
     *
     * @param id the id of the essentialsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/essentials/{id}")
    public ResponseEntity<Void> deleteEssentials(@PathVariable Long id) {
        log.debug("REST request to delete Essentials : {}", id);
        essentialsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
