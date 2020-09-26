package com.gok.campaign.web.rest;

import com.gok.campaign.service.QuestionSetService;
import com.gok.campaign.web.rest.errors.BadRequestAlertException;
import com.gok.campaign.service.dto.QuestionSetDTO;

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
 * REST controller for managing {@link com.gok.campaign.domain.QuestionSet}.
 */
@RestController
@RequestMapping("/api")
public class QuestionSetResource {

    private final Logger log = LoggerFactory.getLogger(QuestionSetResource.class);

    private static final String ENTITY_NAME = "campaignQuestionSet";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final QuestionSetService questionSetService;

    public QuestionSetResource(QuestionSetService questionSetService) {
        this.questionSetService = questionSetService;
    }

    /**
     * {@code POST  /question-sets} : Create a new questionSet.
     *
     * @param questionSetDTO the questionSetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new questionSetDTO, or with status {@code 400 (Bad Request)} if the questionSet has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/question-sets")
    public ResponseEntity<QuestionSetDTO> createQuestionSet(@RequestBody QuestionSetDTO questionSetDTO) throws URISyntaxException {
        log.debug("REST request to save QuestionSet : {}", questionSetDTO);
        if (questionSetDTO.getId() != null) {
            throw new BadRequestAlertException("A new questionSet cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuestionSetDTO result = questionSetService.save(questionSetDTO);
        return ResponseEntity.created(new URI("/api/question-sets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /question-sets} : Updates an existing questionSet.
     *
     * @param questionSetDTO the questionSetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated questionSetDTO,
     * or with status {@code 400 (Bad Request)} if the questionSetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the questionSetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/question-sets")
    public ResponseEntity<QuestionSetDTO> updateQuestionSet(@RequestBody QuestionSetDTO questionSetDTO) throws URISyntaxException {
        log.debug("REST request to update QuestionSet : {}", questionSetDTO);
        if (questionSetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        QuestionSetDTO result = questionSetService.save(questionSetDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, questionSetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /question-sets} : get all the questionSets.
     *
     * @param pageable the pagination information.
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of questionSets in body.
     */
    @GetMapping("/question-sets")
    public ResponseEntity<List<QuestionSetDTO>> getAllQuestionSets(Pageable pageable, @RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get a page of QuestionSets");
        Page<QuestionSetDTO> page;
        if (eagerload) {
            page = questionSetService.findAllWithEagerRelationships(pageable);
        } else {
            page = questionSetService.findAll(pageable);
        }
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /question-sets/:id} : get the "id" questionSet.
     *
     * @param id the id of the questionSetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the questionSetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/question-sets/{id}")
    public ResponseEntity<QuestionSetDTO> getQuestionSet(@PathVariable Long id) {
        log.debug("REST request to get QuestionSet : {}", id);
        Optional<QuestionSetDTO> questionSetDTO = questionSetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(questionSetDTO);
    }

    /**
     * {@code DELETE  /question-sets/:id} : delete the "id" questionSet.
     *
     * @param id the id of the questionSetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/question-sets/{id}")
    public ResponseEntity<Void> deleteQuestionSet(@PathVariable Long id) {
        log.debug("REST request to delete QuestionSet : {}", id);
        questionSetService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
