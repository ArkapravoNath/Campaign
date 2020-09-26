package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.AdditionalSymptoms;
import com.gok.campaign.repository.AdditionalSymptomsRepository;
import com.gok.campaign.service.AdditionalSymptomsService;
import com.gok.campaign.service.dto.AdditionalSymptomsDTO;
import com.gok.campaign.service.mapper.AdditionalSymptomsMapper;

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
 * Integration tests for the {@link AdditionalSymptomsResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class AdditionalSymptomsResourceIT {

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    @Autowired
    private AdditionalSymptomsRepository additionalSymptomsRepository;

    @Autowired
    private AdditionalSymptomsMapper additionalSymptomsMapper;

    @Autowired
    private AdditionalSymptomsService additionalSymptomsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAdditionalSymptomsMockMvc;

    private AdditionalSymptoms additionalSymptoms;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdditionalSymptoms createEntity(EntityManager em) {
        AdditionalSymptoms additionalSymptoms = new AdditionalSymptoms()
            .identifier(DEFAULT_IDENTIFIER);
        return additionalSymptoms;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AdditionalSymptoms createUpdatedEntity(EntityManager em) {
        AdditionalSymptoms additionalSymptoms = new AdditionalSymptoms()
            .identifier(UPDATED_IDENTIFIER);
        return additionalSymptoms;
    }

    @BeforeEach
    public void initTest() {
        additionalSymptoms = createEntity(em);
    }

    @Test
    @Transactional
    public void createAdditionalSymptoms() throws Exception {
        int databaseSizeBeforeCreate = additionalSymptomsRepository.findAll().size();
        // Create the AdditionalSymptoms
        AdditionalSymptomsDTO additionalSymptomsDTO = additionalSymptomsMapper.toDto(additionalSymptoms);
        restAdditionalSymptomsMockMvc.perform(post("/api/additional-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(additionalSymptomsDTO)))
            .andExpect(status().isCreated());

        // Validate the AdditionalSymptoms in the database
        List<AdditionalSymptoms> additionalSymptomsList = additionalSymptomsRepository.findAll();
        assertThat(additionalSymptomsList).hasSize(databaseSizeBeforeCreate + 1);
        AdditionalSymptoms testAdditionalSymptoms = additionalSymptomsList.get(additionalSymptomsList.size() - 1);
        assertThat(testAdditionalSymptoms.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
    }

    @Test
    @Transactional
    public void createAdditionalSymptomsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = additionalSymptomsRepository.findAll().size();

        // Create the AdditionalSymptoms with an existing ID
        additionalSymptoms.setId(1L);
        AdditionalSymptomsDTO additionalSymptomsDTO = additionalSymptomsMapper.toDto(additionalSymptoms);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAdditionalSymptomsMockMvc.perform(post("/api/additional-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(additionalSymptomsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdditionalSymptoms in the database
        List<AdditionalSymptoms> additionalSymptomsList = additionalSymptomsRepository.findAll();
        assertThat(additionalSymptomsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAdditionalSymptoms() throws Exception {
        // Initialize the database
        additionalSymptomsRepository.saveAndFlush(additionalSymptoms);

        // Get all the additionalSymptomsList
        restAdditionalSymptomsMockMvc.perform(get("/api/additional-symptoms?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(additionalSymptoms.getId().intValue())))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER)));
    }
    
    @Test
    @Transactional
    public void getAdditionalSymptoms() throws Exception {
        // Initialize the database
        additionalSymptomsRepository.saveAndFlush(additionalSymptoms);

        // Get the additionalSymptoms
        restAdditionalSymptomsMockMvc.perform(get("/api/additional-symptoms/{id}", additionalSymptoms.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(additionalSymptoms.getId().intValue()))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER));
    }
    @Test
    @Transactional
    public void getNonExistingAdditionalSymptoms() throws Exception {
        // Get the additionalSymptoms
        restAdditionalSymptomsMockMvc.perform(get("/api/additional-symptoms/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAdditionalSymptoms() throws Exception {
        // Initialize the database
        additionalSymptomsRepository.saveAndFlush(additionalSymptoms);

        int databaseSizeBeforeUpdate = additionalSymptomsRepository.findAll().size();

        // Update the additionalSymptoms
        AdditionalSymptoms updatedAdditionalSymptoms = additionalSymptomsRepository.findById(additionalSymptoms.getId()).get();
        // Disconnect from session so that the updates on updatedAdditionalSymptoms are not directly saved in db
        em.detach(updatedAdditionalSymptoms);
        updatedAdditionalSymptoms
            .identifier(UPDATED_IDENTIFIER);
        AdditionalSymptomsDTO additionalSymptomsDTO = additionalSymptomsMapper.toDto(updatedAdditionalSymptoms);

        restAdditionalSymptomsMockMvc.perform(put("/api/additional-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(additionalSymptomsDTO)))
            .andExpect(status().isOk());

        // Validate the AdditionalSymptoms in the database
        List<AdditionalSymptoms> additionalSymptomsList = additionalSymptomsRepository.findAll();
        assertThat(additionalSymptomsList).hasSize(databaseSizeBeforeUpdate);
        AdditionalSymptoms testAdditionalSymptoms = additionalSymptomsList.get(additionalSymptomsList.size() - 1);
        assertThat(testAdditionalSymptoms.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
    }

    @Test
    @Transactional
    public void updateNonExistingAdditionalSymptoms() throws Exception {
        int databaseSizeBeforeUpdate = additionalSymptomsRepository.findAll().size();

        // Create the AdditionalSymptoms
        AdditionalSymptomsDTO additionalSymptomsDTO = additionalSymptomsMapper.toDto(additionalSymptoms);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAdditionalSymptomsMockMvc.perform(put("/api/additional-symptoms").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(additionalSymptomsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AdditionalSymptoms in the database
        List<AdditionalSymptoms> additionalSymptomsList = additionalSymptomsRepository.findAll();
        assertThat(additionalSymptomsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAdditionalSymptoms() throws Exception {
        // Initialize the database
        additionalSymptomsRepository.saveAndFlush(additionalSymptoms);

        int databaseSizeBeforeDelete = additionalSymptomsRepository.findAll().size();

        // Delete the additionalSymptoms
        restAdditionalSymptomsMockMvc.perform(delete("/api/additional-symptoms/{id}", additionalSymptoms.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AdditionalSymptoms> additionalSymptomsList = additionalSymptomsRepository.findAll();
        assertThat(additionalSymptomsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
