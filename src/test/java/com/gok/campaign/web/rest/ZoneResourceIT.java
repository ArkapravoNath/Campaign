package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.Zone;
import com.gok.campaign.repository.ZoneRepository;
import com.gok.campaign.service.ZoneService;
import com.gok.campaign.service.dto.ZoneDTO;
import com.gok.campaign.service.mapper.ZoneMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gok.campaign.domain.enumeration.StatusType;
/**
 * Integration tests for the {@link ZoneResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
@WithMockUser
public class ZoneResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final StatusType DEFAULT_STATUS = StatusType.ACTIVE;
    private static final StatusType UPDATED_STATUS = StatusType.INACTIVE;

    private static final Float DEFAULT_RADIUS = 1F;
    private static final Float UPDATED_RADIUS = 2F;

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    @Autowired
    private ZoneRepository zoneRepository;

    @Mock
    private ZoneRepository zoneRepositoryMock;

    @Autowired
    private ZoneMapper zoneMapper;

    @Mock
    private ZoneService zoneServiceMock;

    @Autowired
    private ZoneService zoneService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restZoneMockMvc;

    private Zone zone;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Zone createEntity(EntityManager em) {
        Zone zone = new Zone()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .radius(DEFAULT_RADIUS)
            .metadata(DEFAULT_METADATA);
        return zone;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Zone createUpdatedEntity(EntityManager em) {
        Zone zone = new Zone()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .radius(UPDATED_RADIUS)
            .metadata(UPDATED_METADATA);
        return zone;
    }

    @BeforeEach
    public void initTest() {
        zone = createEntity(em);
    }

    @Test
    @Transactional
    public void createZone() throws Exception {
        int databaseSizeBeforeCreate = zoneRepository.findAll().size();
        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);
        restZoneMockMvc.perform(post("/api/zones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isCreated());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeCreate + 1);
        Zone testZone = zoneList.get(zoneList.size() - 1);
        assertThat(testZone.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testZone.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testZone.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testZone.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testZone.getRadius()).isEqualTo(DEFAULT_RADIUS);
        assertThat(testZone.getMetadata()).isEqualTo(DEFAULT_METADATA);
    }

    @Test
    @Transactional
    public void createZoneWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = zoneRepository.findAll().size();

        // Create the Zone with an existing ID
        zone.setId(1L);
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // An entity with an existing ID cannot be created, so this API call must fail
        restZoneMockMvc.perform(post("/api/zones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllZones() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get all the zoneList
        restZoneMockMvc.perform(get("/api/zones?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(zone.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].radius").value(hasItem(DEFAULT_RADIUS.doubleValue())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)));
    }
    
    @SuppressWarnings({"unchecked"})
    public void getAllZonesWithEagerRelationshipsIsEnabled() throws Exception {
        when(zoneServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restZoneMockMvc.perform(get("/api/zones?eagerload=true"))
            .andExpect(status().isOk());

        verify(zoneServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @SuppressWarnings({"unchecked"})
    public void getAllZonesWithEagerRelationshipsIsNotEnabled() throws Exception {
        when(zoneServiceMock.findAllWithEagerRelationships(any())).thenReturn(new PageImpl(new ArrayList<>()));

        restZoneMockMvc.perform(get("/api/zones?eagerload=true"))
            .andExpect(status().isOk());

        verify(zoneServiceMock, times(1)).findAllWithEagerRelationships(any());
    }

    @Test
    @Transactional
    public void getZone() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        // Get the zone
        restZoneMockMvc.perform(get("/api/zones/{id}", zone.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(zone.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.radius").value(DEFAULT_RADIUS.doubleValue()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA));
    }
    @Test
    @Transactional
    public void getNonExistingZone() throws Exception {
        // Get the zone
        restZoneMockMvc.perform(get("/api/zones/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateZone() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();

        // Update the zone
        Zone updatedZone = zoneRepository.findById(zone.getId()).get();
        // Disconnect from session so that the updates on updatedZone are not directly saved in db
        em.detach(updatedZone);
        updatedZone
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .radius(UPDATED_RADIUS)
            .metadata(UPDATED_METADATA);
        ZoneDTO zoneDTO = zoneMapper.toDto(updatedZone);

        restZoneMockMvc.perform(put("/api/zones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isOk());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
        Zone testZone = zoneList.get(zoneList.size() - 1);
        assertThat(testZone.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testZone.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testZone.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testZone.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testZone.getRadius()).isEqualTo(UPDATED_RADIUS);
        assertThat(testZone.getMetadata()).isEqualTo(UPDATED_METADATA);
    }

    @Test
    @Transactional
    public void updateNonExistingZone() throws Exception {
        int databaseSizeBeforeUpdate = zoneRepository.findAll().size();

        // Create the Zone
        ZoneDTO zoneDTO = zoneMapper.toDto(zone);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restZoneMockMvc.perform(put("/api/zones").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(zoneDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Zone in the database
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteZone() throws Exception {
        // Initialize the database
        zoneRepository.saveAndFlush(zone);

        int databaseSizeBeforeDelete = zoneRepository.findAll().size();

        // Delete the zone
        restZoneMockMvc.perform(delete("/api/zones/{id}", zone.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Zone> zoneList = zoneRepository.findAll();
        assertThat(zoneList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
