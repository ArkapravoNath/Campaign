package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.MedicalSymptom;
import com.gok.campaign.repository.MedicalSymptomRepository;
import com.gok.campaign.service.MedicalSymptomService;
import com.gok.campaign.service.dto.MedicalSymptomDTO;
import com.gok.campaign.service.mapper.MedicalSymptomMapper;

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

/**
 * Integration tests for the {@link MedicalSymptomResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class MedicalSymptomResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    @Autowired
    private MedicalSymptomRepository medicalSymptomRepository;

    @Autowired
    private MedicalSymptomMapper medicalSymptomMapper;

    @Autowired
    private MedicalSymptomService medicalSymptomService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMedicalSymptomMockMvc;

    private MedicalSymptom medicalSymptom;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalSymptom createEntity(EntityManager em) {
        MedicalSymptom medicalSymptom = new MedicalSymptom()
            .name(DEFAULT_NAME)
            .identifier(DEFAULT_IDENTIFIER)
            .description(DEFAULT_DESCRIPTION)
            .priority(DEFAULT_PRIORITY);
        return medicalSymptom;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MedicalSymptom createUpdatedEntity(EntityManager em) {
        MedicalSymptom medicalSymptom = new MedicalSymptom()
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .description(UPDATED_DESCRIPTION)
            .priority(UPDATED_PRIORITY);
        return medicalSymptom;
    }

    @BeforeEach
    public void initTest() {
        medicalSymptom = createEntity(em);
    }

    @Test
    @Transactional
    public void createMedicalSymptom() throws Exception {
        int databaseSizeBeforeCreate = medicalSymptomRepository.findAll().size();
        // Create the MedicalSymptom
        MedicalSymptomDTO medicalSymptomDTO = medicalSymptomMapper.toDto(medicalSymptom);
        restMedicalSymptomMockMvc.perform(post("/api/medical-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSymptomDTO)))
            .andExpect(status().isCreated());

        // Validate the MedicalSymptom in the database
        List<MedicalSymptom> medicalSymptomList = medicalSymptomRepository.findAll();
        assertThat(medicalSymptomList).hasSize(databaseSizeBeforeCreate + 1);
        MedicalSymptom testMedicalSymptom = medicalSymptomList.get(medicalSymptomList.size() - 1);
        assertThat(testMedicalSymptom.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMedicalSymptom.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testMedicalSymptom.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testMedicalSymptom.getPriority()).isEqualTo(DEFAULT_PRIORITY);
    }

    @Test
    @Transactional
    public void createMedicalSymptomWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = medicalSymptomRepository.findAll().size();

        // Create the MedicalSymptom with an existing ID
        medicalSymptom.setId(1L);
        MedicalSymptomDTO medicalSymptomDTO = medicalSymptomMapper.toDto(medicalSymptom);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMedicalSymptomMockMvc.perform(post("/api/medical-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalSymptom in the database
        List<MedicalSymptom> medicalSymptomList = medicalSymptomRepository.findAll();
        assertThat(medicalSymptomList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllMedicalSymptoms() throws Exception {
        // Initialize the database
        medicalSymptomRepository.saveAndFlush(medicalSymptom);

        // Get all the medicalSymptomList
        restMedicalSymptomMockMvc.perform(get("/api/medical-symptoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(medicalSymptom.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)));
    }
    
    @Test
    @Transactional
    public void getMedicalSymptom() throws Exception {
        // Initialize the database
        medicalSymptomRepository.saveAndFlush(medicalSymptom);

        // Get the medicalSymptom
        restMedicalSymptomMockMvc.perform(get("/api/medical-symptoms/{id}", medicalSymptom.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(medicalSymptom.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY));
    }
    @Test
    @Transactional
    public void getNonExistingMedicalSymptom() throws Exception {
        // Get the medicalSymptom
        restMedicalSymptomMockMvc.perform(get("/api/medical-symptoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMedicalSymptom() throws Exception {
        // Initialize the database
        medicalSymptomRepository.saveAndFlush(medicalSymptom);

        int databaseSizeBeforeUpdate = medicalSymptomRepository.findAll().size();

        // Update the medicalSymptom
        MedicalSymptom updatedMedicalSymptom = medicalSymptomRepository.findById(medicalSymptom.getId()).get();
        // Disconnect from session so that the updates on updatedMedicalSymptom are not directly saved in db
        em.detach(updatedMedicalSymptom);
        updatedMedicalSymptom
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .description(UPDATED_DESCRIPTION)
            .priority(UPDATED_PRIORITY);
        MedicalSymptomDTO medicalSymptomDTO = medicalSymptomMapper.toDto(updatedMedicalSymptom);

        restMedicalSymptomMockMvc.perform(put("/api/medical-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSymptomDTO)))
            .andExpect(status().isOk());

        // Validate the MedicalSymptom in the database
        List<MedicalSymptom> medicalSymptomList = medicalSymptomRepository.findAll();
        assertThat(medicalSymptomList).hasSize(databaseSizeBeforeUpdate);
        MedicalSymptom testMedicalSymptom = medicalSymptomList.get(medicalSymptomList.size() - 1);
        assertThat(testMedicalSymptom.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMedicalSymptom.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testMedicalSymptom.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testMedicalSymptom.getPriority()).isEqualTo(UPDATED_PRIORITY);
    }

    @Test
    @Transactional
    public void updateNonExistingMedicalSymptom() throws Exception {
        int databaseSizeBeforeUpdate = medicalSymptomRepository.findAll().size();

        // Create the MedicalSymptom
        MedicalSymptomDTO medicalSymptomDTO = medicalSymptomMapper.toDto(medicalSymptom);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMedicalSymptomMockMvc.perform(put("/api/medical-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(medicalSymptomDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MedicalSymptom in the database
        List<MedicalSymptom> medicalSymptomList = medicalSymptomRepository.findAll();
        assertThat(medicalSymptomList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteMedicalSymptom() throws Exception {
        // Initialize the database
        medicalSymptomRepository.saveAndFlush(medicalSymptom);

        int databaseSizeBeforeDelete = medicalSymptomRepository.findAll().size();

        // Delete the medicalSymptom
        restMedicalSymptomMockMvc.perform(delete("/api/medical-symptoms/{id}", medicalSymptom.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MedicalSymptom> medicalSymptomList = medicalSymptomRepository.findAll();
        assertThat(medicalSymptomList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
