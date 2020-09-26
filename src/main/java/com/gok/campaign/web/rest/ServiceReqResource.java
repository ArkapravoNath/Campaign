package com.gok.campaign.web.rest;

import com.gok.campaign.service.ServiceReqService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.ServiceReqDTO;

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
import java.util.stream.StreamSupport;

/**
 * REST controller for managing {@link com.gok.campaign.domain.ServiceReq}.
 */
@RestController
@RequestMapping("/api")
public class ServiceReqResource {

    private final Logger log = LoggerFactory.getLogger(ServiceReqResource.class);

    private static final String ENTITY_NAME = "campaignServiceReq";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceReqService serviceReqService;

    public ServiceReqResource(ServiceReqService serviceReqService) {
        this.serviceReqService = serviceReqService;
    }

    /**
     * {@code POST  /service-reqs} : Create a new serviceReq.
     *
     * @param serviceReqDTO the serviceReqDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceReqDTO, or with status {@code 400 (Bad Request)} if the serviceReq has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/service-reqs")
    public ResponseEntity<ServiceReqDTO> createServiceReq(@RequestBody ServiceReqDTO serviceReqDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceReq : {}", serviceReqDTO);
        if (serviceReqDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceReq cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ServiceReqDTO result = serviceReqService.save(serviceReqDTO);
        return ResponseEntity.created(new URI("/api/service-reqs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /service-reqs} : Updates an existing serviceReq.
     *
     * @param serviceReqDTO the serviceReqDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceReqDTO,
     * or with status {@code 400 (Bad Request)} if the serviceReqDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceReqDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/service-reqs")
    public ResponseEntity<ServiceReqDTO> updateServiceReq(@RequestBody ServiceReqDTO serviceReqDTO) throws URISyntaxException {
        log.debug("REST request to update ServiceReq : {}", serviceReqDTO);
        if (serviceReqDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ServiceReqDTO result = serviceReqService.save(serviceReqDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, serviceReqDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /service-reqs} : get all the serviceReqs.
     *
     * @param pageable the pagination information.
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceReqs in body.
     */
    @GetMapping("/service-reqs")
    public ResponseEntity<List<ServiceReqDTO>> getAllServiceReqs(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("essentials-is-null".equals(filter)) {
            log.debug("REST request to get all ServiceReqs where essentials is null");
            return new ResponseEntity<>(serviceReqService.findAllWhereEssentialsIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of ServiceReqs");
        Page<ServiceReqDTO> page = serviceReqService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /service-reqs/:id} : get the "id" serviceReq.
     *
     * @param id the id of the serviceReqDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceReqDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/service-reqs/{id}")
    public ResponseEntity<ServiceReqDTO> getServiceReq(@PathVariable Long id) {
        log.debug("REST request to get ServiceReq : {}", id);
        Optional<ServiceReqDTO> serviceReqDTO = serviceReqService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceReqDTO);
    }

    /**
     * {@code DELETE  /service-reqs/:id} : delete the "id" serviceReq.
     *
     * @param id the id of the serviceReqDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/service-reqs/{id}")
    public ResponseEntity<Void> deleteServiceReq(@PathVariable Long id) {
        log.debug("REST request to delete ServiceReq : {}", id);
        serviceReqService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
