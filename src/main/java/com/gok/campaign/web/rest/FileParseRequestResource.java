package com.gok.campaign.web.rest;

import com.gok.campaign.service.FileParseRequestService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.FileParseRequestDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.FileParseRequest}.
 */
@RestController
@RequestMapping("/api")
public class FileParseRequestResource {

    private final Logger log = LoggerFactory.getLogger(FileParseRequestResource.class);

    private static final String ENTITY_NAME = "campaignFileParseRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FileParseRequestService fileParseRequestService;

    public FileParseRequestResource(FileParseRequestService fileParseRequestService) {
        this.fileParseRequestService = fileParseRequestService;
    }

    /**
     * {@code POST  /file-parse-requests} : Create a new fileParseRequest.
     *
     * @param fileParseRequestDTO the fileParseRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fileParseRequestDTO, or with status {@code 400 (Bad Request)} if the fileParseRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/file-parse-requests")
    public ResponseEntity<FileParseRequestDTO> createFileParseRequest(@RequestBody FileParseRequestDTO fileParseRequestDTO) throws URISyntaxException {
        log.debug("REST request to save FileParseRequest : {}", fileParseRequestDTO);
        if (fileParseRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new fileParseRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FileParseRequestDTO result = fileParseRequestService.save(fileParseRequestDTO);
        return ResponseEntity.created(new URI("/api/file-parse-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /file-parse-requests} : Updates an existing fileParseRequest.
     *
     * @param fileParseRequestDTO the fileParseRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fileParseRequestDTO,
     * or with status {@code 400 (Bad Request)} if the fileParseRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fileParseRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/file-parse-requests")
    public ResponseEntity<FileParseRequestDTO> updateFileParseRequest(@RequestBody FileParseRequestDTO fileParseRequestDTO) throws URISyntaxException {
        log.debug("REST request to update FileParseRequest : {}", fileParseRequestDTO);
        if (fileParseRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FileParseRequestDTO result = fileParseRequestService.save(fileParseRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fileParseRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /file-parse-requests} : get all the fileParseRequests.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fileParseRequests in body.
     */
    @GetMapping("/file-parse-requests")
    public ResponseEntity<List<FileParseRequestDTO>> getAllFileParseRequests(Pageable pageable) {
        log.debug("REST request to get a page of FileParseRequests");
        Page<FileParseRequestDTO> page = fileParseRequestService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /file-parse-requests/:id} : get the "id" fileParseRequest.
     *
     * @param id the id of the fileParseRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fileParseRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/file-parse-requests/{id}")
    public ResponseEntity<FileParseRequestDTO> getFileParseRequest(@PathVariable Long id) {
        log.debug("REST request to get FileParseRequest : {}", id);
        Optional<FileParseRequestDTO> fileParseRequestDTO = fileParseRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fileParseRequestDTO);
    }

    /**
     * {@code DELETE  /file-parse-requests/:id} : delete the "id" fileParseRequest.
     *
     * @param id the id of the fileParseRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/file-parse-requests/{id}")
    public ResponseEntity<Void> deleteFileParseRequest(@PathVariable Long id) {
        log.debug("REST request to delete FileParseRequest : {}", id);
        fileParseRequestService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
