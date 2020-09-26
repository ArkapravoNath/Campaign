package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.AnswerSet;
import com.gok.campaign.repository.AnswerSetRepository;
import com.gok.campaign.service.AnswerSetService;
import com.gok.campaign.service.dto.AnswerSetDTO;
import com.gok.campaign.service.mapper.AnswerSetMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gok.campaign.domain.enumeration.SourceType;
/**
 * Integration tests for the {@link AnswerSetResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class AnswerSetResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final SourceType DEFAULT_SOURCE = SourceType.APTHAMITRA;
    private static final SourceType UPDATED_SOURCE = SourceType.CRM;

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private AnswerSetRepository answerSetRepository;

    @Autowired
    private AnswerSetMapper answerSetMapper;

    @Autowired
    private AnswerSetService answerSetService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAnswerSetMockMvc;

    private AnswerSet answerSet;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSet createEntity(EntityManager em) {
        AnswerSet answerSet = new AnswerSet()
            .name(DEFAULT_NAME)
            .source(DEFAULT_SOURCE)
            .metadata(DEFAULT_METADATA)
            .remarks(DEFAULT_REMARKS);
        return answerSet;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AnswerSet createUpdatedEntity(EntityManager em) {
        AnswerSet answerSet = new AnswerSet()
            .name(UPDATED_NAME)
            .source(UPDATED_SOURCE)
            .metadata(UPDATED_METADATA)
            .remarks(UPDATED_REMARKS);
        return answerSet;
    }

    @BeforeEach
    public void initTest() {
        answerSet = createEntity(em);
    }

    @Test
    @Transactional
    public void createAnswerSet() throws Exception {
        int databaseSizeBeforeCreate = answerSetRepository.findAll().size();
        // Create the AnswerSet
        AnswerSetDTO answerSetDTO = answerSetMapper.toDto(answerSet);
        restAnswerSetMockMvc.perform(post("/api/answer-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answerSetDTO)))
            .andExpect(status().isCreated());

        // Validate the AnswerSet in the database
        List<AnswerSet> answerSetList = answerSetRepository.findAll();
        assertThat(answerSetList).hasSize(databaseSizeBeforeCreate + 1);
        AnswerSet testAnswerSet = answerSetList.get(answerSetList.size() - 1);
        assertThat(testAnswerSet.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testAnswerSet.getSource()).isEqualTo(DEFAULT_SOURCE);
        assertThat(testAnswerSet.getMetadata()).isEqualTo(DEFAULT_METADATA);
        assertThat(testAnswerSet.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createAnswerSetWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = answerSetRepository.findAll().size();

        // Create the AnswerSet with an existing ID
        answerSet.setId(1L);
        AnswerSetDTO answerSetDTO = answerSetMapper.toDto(answerSet);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAnswerSetMockMvc.perform(post("/api/answer-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answerSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnswerSet in the database
        List<AnswerSet> answerSetList = answerSetRepository.findAll();
        assertThat(answerSetList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAnswerSets() throws Exception {
        // Initialize the database
        answerSetRepository.saveAndFlush(answerSet);

        // Get all the answerSetList
        restAnswerSetMockMvc.perform(get("/api/answer-sets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(answerSet.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE.toString())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));
    }
    
    @Test
    @Transactional
    public void getAnswerSet() throws Exception {
        // Initialize the database
        answerSetRepository.saveAndFlush(answerSet);

        // Get the answerSet
        restAnswerSetMockMvc.perform(get("/api/answer-sets/{id}", answerSet.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(answerSet.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE.toString()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS));
    }
    @Test
    @Transactional
    public void getNonExistingAnswerSet() throws Exception {
        // Get the answerSet
        restAnswerSetMockMvc.perform(get("/api/answer-sets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAnswerSet() throws Exception {
        // Initialize the database
        answerSetRepository.saveAndFlush(answerSet);

        int databaseSizeBeforeUpdate = answerSetRepository.findAll().size();

        // Update the answerSet
        AnswerSet updatedAnswerSet = answerSetRepository.findById(answerSet.getId()).get();
        // Disconnect from session so that the updates on updatedAnswerSet are not directly saved in db
        em.detach(updatedAnswerSet);
        updatedAnswerSet
            .name(UPDATED_NAME)
            .source(UPDATED_SOURCE)
            .metadata(UPDATED_METADATA)
            .remarks(UPDATED_REMARKS);
        AnswerSetDTO answerSetDTO = answerSetMapper.toDto(updatedAnswerSet);

        restAnswerSetMockMvc.perform(put("/api/answer-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answerSetDTO)))
            .andExpect(status().isOk());

        // Validate the AnswerSet in the database
        List<AnswerSet> answerSetList = answerSetRepository.findAll();
        assertThat(answerSetList).hasSize(databaseSizeBeforeUpdate);
        AnswerSet testAnswerSet = answerSetList.get(answerSetList.size() - 1);
        assertThat(testAnswerSet.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testAnswerSet.getSource()).isEqualTo(UPDATED_SOURCE);
        assertThat(testAnswerSet.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testAnswerSet.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingAnswerSet() throws Exception {
        int databaseSizeBeforeUpdate = answerSetRepository.findAll().size();

        // Create the AnswerSet
        AnswerSetDTO answerSetDTO = answerSetMapper.toDto(answerSet);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAnswerSetMockMvc.perform(put("/api/answer-sets").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(answerSetDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AnswerSet in the database
        List<AnswerSet> answerSetList = answerSetRepository.findAll();
        assertThat(answerSetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAnswerSet() throws Exception {
        // Initialize the database
        answerSetRepository.saveAndFlush(answerSet);

        int databaseSizeBeforeDelete = answerSetRepository.findAll().size();

        // Delete the answerSet
        restAnswerSetMockMvc.perform(delete("/api/answer-sets/{id}", answerSet.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AnswerSet> answerSetList = answerSetRepository.findAll();
        assertThat(answerSetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
