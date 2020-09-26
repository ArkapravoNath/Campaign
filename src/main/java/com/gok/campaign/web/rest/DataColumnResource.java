package com.gok.campaign.web.rest;

import com.gok.campaign.service.DataColumnService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.DataColumnDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.DataColumn}.
 */
@RestController
@RequestMapping("/api")
public class DataColumnResource {

    private final Logger log = LoggerFactory.getLogger(DataColumnResource.class);

    private static final String ENTITY_NAME = "campaignDataColumn";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataColumnService dataColumnService;

    public DataColumnResource(DataColumnService dataColumnService) {
        this.dataColumnService = dataColumnService;
    }

    /**
     * {@code POST  /data-columns} : Create a new dataColumn.
     *
     * @param dataColumnDTO the dataColumnDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataColumnDTO, or with status {@code 400 (Bad Request)} if the dataColumn has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-columns")
    public ResponseEntity<DataColumnDTO> createDataColumn(@RequestBody DataColumnDTO dataColumnDTO) throws URISyntaxException {
        log.debug("REST request to save DataColumn : {}", dataColumnDTO);
        if (dataColumnDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataColumn cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataColumnDTO result = dataColumnService.save(dataColumnDTO);
        return ResponseEntity.created(new URI("/api/data-columns/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-columns} : Updates an existing dataColumn.
     *
     * @param dataColumnDTO the dataColumnDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataColumnDTO,
     * or with status {@code 400 (Bad Request)} if the dataColumnDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataColumnDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-columns")
    public ResponseEntity<DataColumnDTO> updateDataColumn(@RequestBody DataColumnDTO dataColumnDTO) throws URISyntaxException {
        log.debug("REST request to update DataColumn : {}", dataColumnDTO);
        if (dataColumnDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataColumnDTO result = dataColumnService.save(dataColumnDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataColumnDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-columns} : get all the dataColumns.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataColumns in body.
     */
    @GetMapping("/data-columns")
    public ResponseEntity<List<DataColumnDTO>> getAllDataColumns(Pageable pageable) {
        log.debug("REST request to get a page of DataColumns");
        Page<DataColumnDTO> page = dataColumnService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-columns/:id} : get the "id" dataColumn.
     *
     * @param id the id of the dataColumnDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataColumnDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-columns/{id}")
    public ResponseEntity<DataColumnDTO> getDataColumn(@PathVariable Long id) {
        log.debug("REST request to get DataColumn : {}", id);
        Optional<DataColumnDTO> dataColumnDTO = dataColumnService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataColumnDTO);
    }

    /**
     * {@code DELETE  /data-columns/:id} : delete the "id" dataColumn.
     *
     * @param id the id of the dataColumnDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-columns/{id}")
    public ResponseEntity<Void> deleteDataColumn(@PathVariable Long id) {
        log.debug("REST request to delete DataColumn : {}", id);
        dataColumnService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
