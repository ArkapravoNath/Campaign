package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.ZoneType;
import com.gok.campaign.repository.ZoneTypeRepository;
import com.gok.campaign.service.ZoneTypeService;
import com.gok.campaign.service.dto.ZoneTypeDTO;
import com.gok.campaign.service.mapper.ZoneTypeMapper;

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
 * Integration tests for the {@link ZoneTypeResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class ZoneTypeResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private ZoneTypeRepository zoneTypeRepository;

    @Autowired
    private ZoneTypeMapper zoneTypeMapper;

    @Autowired
    private ZoneTypeService zoneTypeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restZoneTypeMockMvc;

    private ZoneType zoneType;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ZoneType createEntity(EntityManager em) {
        ZoneType zoneType = new ZoneType()
            .name(DEFAULT_NAME)
            .identifier(DEFAULT_IDENTIFIER)
            .description(DEFAULT_DESCRIPTION);
        return zoneType;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ZoneType createUpdatedEntity(EntityManager em) {
        ZoneType zoneType = new ZoneType()
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .description(UPDATED_DESCRIPTION);
        return zoneType;
    }

    @BeforeEach
    public void initTest() {
        zoneType = createEntity(em);
    }

    @Test
    @Transactional
    public void createZoneType() throws Exception {
        int databaseSizeBeforeCreate = zoneTypeRepository.findAll().size();
        // Create the ZoneType
        ZoneTypeDTO zoneTypeDTO = zoneTypeMapper.toDto(zoneType);
        restZoneTypeMockMvc.perform(post("/api/zone-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneTypeDTO)))
            .andExpect(status().isCreated());

        // Validate the ZoneType in the database
        List<ZoneType> zoneTypeList = zoneTypeRepository.findAll();
        assertThat(zoneTypeList).hasSize(databaseSizeBeforeCreate + 1);
        ZoneType testZoneType = zoneTypeList.get(zoneTypeList.size() - 1);
        assertThat(testZoneType.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testZoneType.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testZoneType.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createZoneTypeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = zoneTypeRepository.findAll().size();

        // Create the ZoneType with an existing ID
        zoneType.setId(1L);
        ZoneTypeDTO zoneTypeDTO = zoneTypeMapper.toDto(zoneType);

        // An entity with an existing ID cannot be created, so this API call must fail
        restZoneTypeMockMvc.perform(post("/api/zone-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZoneType in the database
        List<ZoneType> zoneTypeList = zoneTypeRepository.findAll();
        assertThat(zoneTypeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllZoneTypes() throws Exception {
        // Initialize the database
        zoneTypeRepository.saveAndFlush(zoneType);

        // Get all the zoneTypeList
        restZoneTypeMockMvc.perform(get("/api/zone-types?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zoneType.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getZoneType() throws Exception {
        // Initialize the database
        zoneTypeRepository.saveAndFlush(zoneType);

        // Get the zoneType
        restZoneTypeMockMvc.perform(get("/api/zone-types/{id}", zoneType.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(zoneType.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingZoneType() throws Exception {
        // Get the zoneType
        restZoneTypeMockMvc.perform(get("/api/zone-types/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZoneType() throws Exception {
        // Initialize the database
        zoneTypeRepository.saveAndFlush(zoneType);

        int databaseSizeBeforeUpdate = zoneTypeRepository.findAll().size();

        // Update the zoneType
        ZoneType updatedZoneType = zoneTypeRepository.findById(zoneType.getId()).get();
        // Disconnect from session so that the updates on updatedZoneType are not directly saved in db
        em.detach(updatedZoneType);
        updatedZoneType
            .name(UPDATED_NAME)
            .identifier(UPDATED_IDENTIFIER)
            .description(UPDATED_DESCRIPTION);
        ZoneTypeDTO zoneTypeDTO = zoneTypeMapper.toDto(updatedZoneType);

        restZoneTypeMockMvc.perform(put("/api/zone-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneTypeDTO)))
            .andExpect(status().isOk());

        // Validate the ZoneType in the database
        List<ZoneType> zoneTypeList = zoneTypeRepository.findAll();
        assertThat(zoneTypeList).hasSize(databaseSizeBeforeUpdate);
        ZoneType testZoneType = zoneTypeList.get(zoneTypeList.size() - 1);
        assertThat(testZoneType.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testZoneType.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testZoneType.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingZoneType() throws Exception {
        int databaseSizeBeforeUpdate = zoneTypeRepository.findAll().size();

        // Create the ZoneType
        ZoneTypeDTO zoneTypeDTO = zoneTypeMapper.toDto(zoneType);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZoneTypeMockMvc.perform(put("/api/zone-types").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneTypeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ZoneType in the database
        List<ZoneType> zoneTypeList = zoneTypeRepository.findAll();
        assertThat(zoneTypeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteZoneType() throws Exception {
        // Initialize the database
        zoneTypeRepository.saveAndFlush(zoneType);

        int databaseSizeBeforeDelete = zoneTypeRepository.findAll().size();

        // Delete the zoneType
        restZoneTypeMockMvc.perform(delete("/api/zone-types/{id}", zoneType.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ZoneType> zoneTypeList = zoneTypeRepository.findAll();
        assertThat(zoneTypeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
