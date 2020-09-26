package com.gok.campaign.web.rest;

import com.gok.campaign.service.ColumnMasterService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.ColumnMasterDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.ColumnMaster}.
 */
@RestController
@RequestMapping("/api")
public class ColumnMasterResource {

    private final Logger log = LoggerFactory.getLogger(ColumnMasterResource.class);

    private static final String ENTITY_NAME = "campaignColumnMaster";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ColumnMasterService columnMasterService;

    public ColumnMasterResource(ColumnMasterService columnMasterService) {
        this.columnMasterService = columnMasterService;
    }

    /**
     * {@code POST  /column-masters} : Create a new columnMaster.
     *
     * @param columnMasterDTO the columnMasterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new columnMasterDTO, or with status {@code 400 (Bad Request)} if the columnMaster has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/column-masters")
    public ResponseEntity<ColumnMasterDTO> createColumnMaster(@RequestBody ColumnMasterDTO columnMasterDTO) throws URISyntaxException {
        log.debug("REST request to save ColumnMaster : {}", columnMasterDTO);
        if (columnMasterDTO.getId() != null) {
            throw new BadRequestAlertException("A new columnMaster cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ColumnMasterDTO result = columnMasterService.save(columnMasterDTO);
        return ResponseEntity.created(new URI("/api/column-masters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /column-masters} : Updates an existing columnMaster.
     *
     * @param columnMasterDTO the columnMasterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated columnMasterDTO,
     * or with status {@code 400 (Bad Request)} if the columnMasterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the columnMasterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/column-masters")
    public ResponseEntity<ColumnMasterDTO> updateColumnMaster(@RequestBody ColumnMasterDTO columnMasterDTO) throws URISyntaxException {
        log.debug("REST request to update ColumnMaster : {}", columnMasterDTO);
        if (columnMasterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ColumnMasterDTO result = columnMasterService.save(columnMasterDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, columnMasterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /column-masters} : get all the columnMasters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of columnMasters in body.
     */
    @GetMapping("/column-masters")
    public ResponseEntity<List<ColumnMasterDTO>> getAllColumnMasters(Pageable pageable) {
        log.debug("REST request to get a page of ColumnMasters");
        Page<ColumnMasterDTO> page = columnMasterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /column-masters/:id} : get the "id" columnMaster.
     *
     * @param id the id of the columnMasterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the columnMasterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/column-masters/{id}")
    public ResponseEntity<ColumnMasterDTO> getColumnMaster(@PathVariable Long id) {
        log.debug("REST request to get ColumnMaster : {}", id);
        Optional<ColumnMasterDTO> columnMasterDTO = columnMasterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(columnMasterDTO);
    }

    /**
     * {@code DELETE  /column-masters/:id} : delete the "id" columnMaster.
     *
     * @param id the id of the columnMasterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/column-masters/{id}")
    public ResponseEntity<Void> deleteColumnMaster(@PathVariable Long id) {
        log.debug("REST request to delete ColumnMaster : {}", id);
        columnMasterService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
