package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.CitizenMedical;
import com.gok.campaign.repository.CitizenMedicalRepository;
import com.gok.campaign.service.CitizenMedicalService;
import com.gok.campaign.service.dto.CitizenMedicalDTO;
import com.gok.campaign.service.mapper.CitizenMedicalMapper;

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
 * Integration tests for the {@link CitizenMedicalResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CitizenMedicalResourceIT {

    private static final String DEFAULT_MEASUREMENT = "AAAAAAAAAA";
    private static final String UPDATED_MEASUREMENT = "BBBBBBBBBB";

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    @Autowired
    private CitizenMedicalRepository citizenMedicalRepository;

    @Autowired
    private CitizenMedicalMapper citizenMedicalMapper;

    @Autowired
    private CitizenMedicalService citizenMedicalService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCitizenMedicalMockMvc;

    private CitizenMedical citizenMedical;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CitizenMedical createEntity(EntityManager em) {
        CitizenMedical citizenMedical = new CitizenMedical()
            .measurement(DEFAULT_MEASUREMENT)
            .remark(DEFAULT_REMARK);
        return citizenMedical;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CitizenMedical createUpdatedEntity(EntityManager em) {
        CitizenMedical citizenMedical = new CitizenMedical()
            .measurement(UPDATED_MEASUREMENT)
            .remark(UPDATED_REMARK);
        return citizenMedical;
    }

    @BeforeEach
    public void initTest() {
        citizenMedical = createEntity(em);
    }

    @Test
    @Transactional
    public void createCitizenMedical() throws Exception {
        int databaseSizeBeforeCreate = citizenMedicalRepository.findAll().size();
        // Create the CitizenMedical
        CitizenMedicalDTO citizenMedicalDTO = citizenMedicalMapper.toDto(citizenMedical);
        restCitizenMedicalMockMvc.perform(post("/api/citizen-medicals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citizenMedicalDTO)))
            .andExpect(status().isCreated());

        // Validate the CitizenMedical in the database
        List<CitizenMedical> citizenMedicalList = citizenMedicalRepository.findAll();
        assertThat(citizenMedicalList).hasSize(databaseSizeBeforeCreate + 1);
        CitizenMedical testCitizenMedical = citizenMedicalList.get(citizenMedicalList.size() - 1);
        assertThat(testCitizenMedical.getMeasurement()).isEqualTo(DEFAULT_MEASUREMENT);
        assertThat(testCitizenMedical.getRemark()).isEqualTo(DEFAULT_REMARK);
    }

    @Test
    @Transactional
    public void createCitizenMedicalWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = citizenMedicalRepository.findAll().size();

        // Create the CitizenMedical with an existing ID
        citizenMedical.setId(1L);
        CitizenMedicalDTO citizenMedicalDTO = citizenMedicalMapper.toDto(citizenMedical);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCitizenMedicalMockMvc.perform(post("/api/citizen-medicals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citizenMedicalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CitizenMedical in the database
        List<CitizenMedical> citizenMedicalList = citizenMedicalRepository.findAll();
        assertThat(citizenMedicalList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCitizenMedicals() throws Exception {
        // Initialize the database
        citizenMedicalRepository.saveAndFlush(citizenMedical);

        // Get all the citizenMedicalList
        restCitizenMedicalMockMvc.perform(get("/api/citizen-medicals?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(citizenMedical.getId().intValue())))
            .andExpect(jsonPath("$.[*].measurement").value(hasItem(DEFAULT_MEASUREMENT)))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK)));
    }
    
    @Test
    @Transactional
    public void getCitizenMedical() throws Exception {
        // Initialize the database
        citizenMedicalRepository.saveAndFlush(citizenMedical);

        // Get the citizenMedical
        restCitizenMedicalMockMvc.perform(get("/api/citizen-medicals/{id}", citizenMedical.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(citizenMedical.getId().intValue()))
            .andExpect(jsonPath("$.measurement").value(DEFAULT_MEASUREMENT))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK));
    }
    @Test
    @Transactional
    public void getNonExistingCitizenMedical() throws Exception {
        // Get the citizenMedical
        restCitizenMedicalMockMvc.perform(get("/api/citizen-medicals/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCitizenMedical() throws Exception {
        // Initialize the database
        citizenMedicalRepository.saveAndFlush(citizenMedical);

        int databaseSizeBeforeUpdate = citizenMedicalRepository.findAll().size();

        // Update the citizenMedical
        CitizenMedical updatedCitizenMedical = citizenMedicalRepository.findById(citizenMedical.getId()).get();
        // Disconnect from session so that the updates on updatedCitizenMedical are not directly saved in db
        em.detach(updatedCitizenMedical);
        updatedCitizenMedical
            .measurement(UPDATED_MEASUREMENT)
            .remark(UPDATED_REMARK);
        CitizenMedicalDTO citizenMedicalDTO = citizenMedicalMapper.toDto(updatedCitizenMedical);

        restCitizenMedicalMockMvc.perform(put("/api/citizen-medicals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citizenMedicalDTO)))
            .andExpect(status().isOk());

        // Validate the CitizenMedical in the database
        List<CitizenMedical> citizenMedicalList = citizenMedicalRepository.findAll();
        assertThat(citizenMedicalList).hasSize(databaseSizeBeforeUpdate);
        CitizenMedical testCitizenMedical = citizenMedicalList.get(citizenMedicalList.size() - 1);
        assertThat(testCitizenMedical.getMeasurement()).isEqualTo(UPDATED_MEASUREMENT);
        assertThat(testCitizenMedical.getRemark()).isEqualTo(UPDATED_REMARK);
    }

    @Test
    @Transactional
    public void updateNonExistingCitizenMedical() throws Exception {
        int databaseSizeBeforeUpdate = citizenMedicalRepository.findAll().size();

        // Create the CitizenMedical
        CitizenMedicalDTO citizenMedicalDTO = citizenMedicalMapper.toDto(citizenMedical);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCitizenMedicalMockMvc.perform(put("/api/citizen-medicals").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(citizenMedicalDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CitizenMedical in the database
        List<CitizenMedical> citizenMedicalList = citizenMedicalRepository.findAll();
        assertThat(citizenMedicalList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCitizenMedical() throws Exception {
        // Initialize the database
        citizenMedicalRepository.saveAndFlush(citizenMedical);

        int databaseSizeBeforeDelete = citizenMedicalRepository.findAll().size();

        // Delete the citizenMedical
        restCitizenMedicalMockMvc.perform(delete("/api/citizen-medicals/{id}", citizenMedical.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CitizenMedical> citizenMedicalList = citizenMedicalRepository.findAll();
        assertThat(citizenMedicalList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
