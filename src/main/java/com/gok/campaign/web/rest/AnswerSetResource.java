package com.gok.campaign.web.rest;

import com.gok.campaign.service.AnswerSetService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.AnswerSetDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.AnswerSet}.
 */
@RestController
@RequestMapping("/api")
public class AnswerSetResource {

    private final Logger log = LoggerFactory.getLogger(AnswerSetResource.class);

    private static final String ENTITY_NAME = "campaignAnswerSet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AnswerSetService answerSetService;

    public AnswerSetResource(AnswerSetService answerSetService) {
        this.answerSetService = answerSetService;
    }

    /**
     * {@code POST  /answer-sets} : Create a new answerSet.
     *
     * @param answerSetDTO the answerSetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new answerSetDTO, or with status {@code 400 (Bad Request)} if the answerSet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/answer-sets")
    public ResponseEntity<AnswerSetDTO> createAnswerSet(@RequestBody AnswerSetDTO answerSetDTO) throws URISyntaxException {
        log.debug("REST request to save AnswerSet : {}", answerSetDTO);
        if (answerSetDTO.getId() != null) {
            throw new BadRequestAlertException("A new answerSet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AnswerSetDTO result = answerSetService.save(answerSetDTO);
        return ResponseEntity.created(new URI("/api/answer-sets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /answer-sets} : Updates an existing answerSet.
     *
     * @param answerSetDTO the answerSetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated answerSetDTO,
     * or with status {@code 400 (Bad Request)} if the answerSetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the answerSetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/answer-sets")
    public ResponseEntity<AnswerSetDTO> updateAnswerSet(@RequestBody AnswerSetDTO answerSetDTO) throws URISyntaxException {
        log.debug("REST request to update AnswerSet : {}", answerSetDTO);
        if (answerSetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AnswerSetDTO result = answerSetService.save(answerSetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, answerSetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /answer-sets} : get all the answerSets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of answerSets in body.
     */
    @GetMapping("/answer-sets")
    public ResponseEntity<List<AnswerSetDTO>> getAllAnswerSets(Pageable pageable) {
        log.debug("REST request to get a page of AnswerSets");
        Page<AnswerSetDTO> page = answerSetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /answer-sets/:id} : get the "id" answerSet.
     *
     * @param id the id of the answerSetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the answerSetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/answer-sets/{id}")
    public ResponseEntity<AnswerSetDTO> getAnswerSet(@PathVariable Long id) {
        log.debug("REST request to get AnswerSet : {}", id);
        Optional<AnswerSetDTO> answerSetDTO = answerSetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(answerSetDTO);
    }

    /**
     * {@code DELETE  /answer-sets/:id} : delete the "id" answerSet.
     *
     * @param id the id of the answerSetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/answer-sets/{id}")
    public ResponseEntity<Void> deleteAnswerSet(@PathVariable Long id) {
        log.debug("REST request to delete AnswerSet : {}", id);
        answerSetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
