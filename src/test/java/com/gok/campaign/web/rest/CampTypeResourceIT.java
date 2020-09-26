package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.CampType;
import com.gok.campaign.repository.CampTypeRepository;
import com.gok.campaign.service.CampTypeService;
import com.gok.campaign.service.dto.CampTypeDTO;
import com.gok.campaign.service.mapper.CampTypeMapper;

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
 * Integration tests for the {@link CampTypeResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CampTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    @Autowired
    private CampTypeRepository campTypeRepository;

    @Autowired
    private CampTypeMapper campTypeMapper;

    @Autowired
    private CampTypeService campTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCampTypeMockMvc;

    private CampType campType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampType createEntity(EntityManager em) {
        CampType campType = new CampType()
            .name(DEFAULT_NAME)
            .metadata(DEFAULT_METADATA);
        return campType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static CampType createUpdatedEntity(EntityManager em) {
        CampType campType = new CampType()
            .name(UPDATED_NAME)
            .metadata(UPDATED_METADATA);
        return campType;
    }

    @BeforeEach
    public void initTest() {
        campType = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampType() throws Exception {
        int databaseSizeBeforeCreate = campTypeRepository.findAll().size();
        // Create the CampType
        CampTypeDTO campTypeDTO = campTypeMapper.toDto(campType);
        restCampTypeMockMvc.perform(post("/api/camp-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the CampType in the database
        List<CampType> campTypeList = campTypeRepository.findAll();
        assertThat(campTypeList).hasSize(databaseSizeBeforeCreate + 1);
        CampType testCampType = campTypeList.get(campTypeList.size() - 1);
        assertThat(testCampType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCampType.getMetadata()).isEqualTo(DEFAULT_METADATA);
    }

    @Test
    @Transactional
    public void createCampTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campTypeRepository.findAll().size();

        // Create the CampType with an existing ID
        campType.setId(1L);
        CampTypeDTO campTypeDTO = campTypeMapper.toDto(campType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampTypeMockMvc.perform(post("/api/camp-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampType in the database
        List<CampType> campTypeList = campTypeRepository.findAll();
        assertThat(campTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllCampTypes() throws Exception {
        // Initialize the database
        campTypeRepository.saveAndFlush(campType);

        // Get all the campTypeList
        restCampTypeMockMvc.perform(get("/api/camp-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)));
    }
    
    @Test
    @Transactional
    public void getCampType() throws Exception {
        // Initialize the database
        campTypeRepository.saveAndFlush(campType);

        // Get the campType
        restCampTypeMockMvc.perform(get("/api/camp-types/{id}", campType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(campType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA));
    }
    @Test
    @Transactional
    public void getNonExistingCampType() throws Exception {
        // Get the campType
        restCampTypeMockMvc.perform(get("/api/camp-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampType() throws Exception {
        // Initialize the database
        campTypeRepository.saveAndFlush(campType);

        int databaseSizeBeforeUpdate = campTypeRepository.findAll().size();

        // Update the campType
        CampType updatedCampType = campTypeRepository.findById(campType.getId()).get();
        // Disconnect from session so that the updates on updatedCampType are not directly saved in db
        em.detach(updatedCampType);
        updatedCampType
            .name(UPDATED_NAME)
            .metadata(UPDATED_METADATA);
        CampTypeDTO campTypeDTO = campTypeMapper.toDto(updatedCampType);

        restCampTypeMockMvc.perform(put("/api/camp-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campTypeDTO)))
            .andExpect(status().isOk());

        // Validate the CampType in the database
        List<CampType> campTypeList = campTypeRepository.findAll();
        assertThat(campTypeList).hasSize(databaseSizeBeforeUpdate);
        CampType testCampType = campTypeList.get(campTypeList.size() - 1);
        assertThat(testCampType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCampType.getMetadata()).isEqualTo(UPDATED_METADATA);
    }

    @Test
    @Transactional
    public void updateNonExistingCampType() throws Exception {
        int databaseSizeBeforeUpdate = campTypeRepository.findAll().size();

        // Create the CampType
        CampTypeDTO campTypeDTO = campTypeMapper.toDto(campType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampTypeMockMvc.perform(put("/api/camp-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the CampType in the database
        List<CampType> campTypeList = campTypeRepository.findAll();
        assertThat(campTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampType() throws Exception {
        // Initialize the database
        campTypeRepository.saveAndFlush(campType);

        int databaseSizeBeforeDelete = campTypeRepository.findAll().size();

        // Delete the campType
        restCampTypeMockMvc.perform(delete("/api/camp-types/{id}", campType.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<CampType> campTypeList = campTypeRepository.findAll();
        assertThat(campTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
