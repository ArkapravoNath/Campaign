package com.gok.campaign.web.rest;

import com.gok.campaign.service.ZoneTypeService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.ZoneTypeDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.ZoneType}.
 */
@RestController
@RequestMapping("/api")
public class ZoneTypeResource {

    private final Logger log = LoggerFactory.getLogger(ZoneTypeResource.class);

    private static final String ENTITY_NAME = "campaignZoneType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ZoneTypeService zoneTypeService;

    public ZoneTypeResource(ZoneTypeService zoneTypeService) {
        this.zoneTypeService = zoneTypeService;
    }

    /**
     * {@code POST  /zone-types} : Create a new zoneType.
     *
     * @param zoneTypeDTO the zoneTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new zoneTypeDTO, or with status {@code 400 (Bad Request)} if the zoneType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/zone-types")
    public ResponseEntity<ZoneTypeDTO> createZoneType(@RequestBody ZoneTypeDTO zoneTypeDTO) throws URISyntaxException {
        log.debug("REST request to save ZoneType : {}", zoneTypeDTO);
        if (zoneTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new zoneType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ZoneTypeDTO result = zoneTypeService.save(zoneTypeDTO);
        return ResponseEntity.created(new URI("/api/zone-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /zone-types} : Updates an existing zoneType.
     *
     * @param zoneTypeDTO the zoneTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated zoneTypeDTO,
     * or with status {@code 400 (Bad Request)} if the zoneTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the zoneTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/zone-types")
    public ResponseEntity<ZoneTypeDTO> updateZoneType(@RequestBody ZoneTypeDTO zoneTypeDTO) throws URISyntaxException {
        log.debug("REST request to update ZoneType : {}", zoneTypeDTO);
        if (zoneTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ZoneTypeDTO result = zoneTypeService.save(zoneTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, zoneTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /zone-types} : get all the zoneTypes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of zoneTypes in body.
     */
    @GetMapping("/zone-types")
    public ResponseEntity<List<ZoneTypeDTO>> getAllZoneTypes(Pageable pageable) {
        log.debug("REST request to get a page of ZoneTypes");
        Page<ZoneTypeDTO> page = zoneTypeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /zone-types/:id} : get the "id" zoneType.
     *
     * @param id the id of the zoneTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the zoneTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/zone-types/{id}")
    public ResponseEntity<ZoneTypeDTO> getZoneType(@PathVariable Long id) {
        log.debug("REST request to get ZoneType : {}", id);
        Optional<ZoneTypeDTO> zoneTypeDTO = zoneTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(zoneTypeDTO);
    }

    /**
     * {@code DELETE  /zone-types/:id} : delete the "id" zoneType.
     *
     * @param id the id of the zoneTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/zone-types/{id}")
    public ResponseEntity<Void> deleteZoneType(@PathVariable Long id) {
        log.debug("REST request to delete ZoneType : {}", id);
        zoneTypeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
