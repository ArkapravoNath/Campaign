package com.gok.campaign.web.rest;

import com.gok.campaign.service.DataRowService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.DataRowDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.DataRow}.
 */
@RestController
@RequestMapping("/api")
public class DataRowResource {

    private final Logger log = LoggerFactory.getLogger(DataRowResource.class);

    private static final String ENTITY_NAME = "campaignDataRow";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DataRowService dataRowService;

    public DataRowResource(DataRowService dataRowService) {
        this.dataRowService = dataRowService;
    }

    /**
     * {@code POST  /data-rows} : Create a new dataRow.
     *
     * @param dataRowDTO the dataRowDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dataRowDTO, or with status {@code 400 (Bad Request)} if the dataRow has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/data-rows")
    public ResponseEntity<DataRowDTO> createDataRow(@RequestBody DataRowDTO dataRowDTO) throws URISyntaxException {
        log.debug("REST request to save DataRow : {}", dataRowDTO);
        if (dataRowDTO.getId() != null) {
            throw new BadRequestAlertException("A new dataRow cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DataRowDTO result = dataRowService.save(dataRowDTO);
        return ResponseEntity.created(new URI("/api/data-rows/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /data-rows} : Updates an existing dataRow.
     *
     * @param dataRowDTO the dataRowDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dataRowDTO,
     * or with status {@code 400 (Bad Request)} if the dataRowDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dataRowDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/data-rows")
    public ResponseEntity<DataRowDTO> updateDataRow(@RequestBody DataRowDTO dataRowDTO) throws URISyntaxException {
        log.debug("REST request to update DataRow : {}", dataRowDTO);
        if (dataRowDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DataRowDTO result = dataRowService.save(dataRowDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, dataRowDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /data-rows} : get all the dataRows.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dataRows in body.
     */
    @GetMapping("/data-rows")
    public ResponseEntity<List<DataRowDTO>> getAllDataRows(Pageable pageable) {
        log.debug("REST request to get a page of DataRows");
        Page<DataRowDTO> page = dataRowService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /data-rows/:id} : get the "id" dataRow.
     *
     * @param id the id of the dataRowDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dataRowDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/data-rows/{id}")
    public ResponseEntity<DataRowDTO> getDataRow(@PathVariable Long id) {
        log.debug("REST request to get DataRow : {}", id);
        Optional<DataRowDTO> dataRowDTO = dataRowService.findOne(id);
        return ResponseUtil.wrapOrNotFound(dataRowDTO);
    }

    /**
     * {@code DELETE  /data-rows/:id} : delete the "id" dataRow.
     *
     * @param id the id of the dataRowDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/data-rows/{id}")
    public ResponseEntity<Void> deleteDataRow(@PathVariable Long id) {
        log.debug("REST request to delete DataRow : {}", id);
        dataRowService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
