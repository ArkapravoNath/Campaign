package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.Campaign;
import com.gok.campaign.repository.CampaignRepository;
import com.gok.campaign.service.CampaignService;
import com.gok.campaign.service.dto.CampaignDTO;
import com.gok.campaign.service.mapper.CampaignMapper;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.gok.campaign.domain.enumeration.StatusType;
/**
 * Integration tests for the {@link CampaignResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class CampaignResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CODE = "BBBBBBBBBB";

    private static final StatusType DEFAULT_STATUS = StatusType.ACTIVE;
    private static final StatusType UPDATED_STATUS = StatusType.INACTIVE;

    private static final Instant DEFAULT_START_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_START_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    private static final Boolean DEFAULT_MANIPULATE_SYMPTOM = false;
    private static final Boolean UPDATED_MANIPULATE_SYMPTOM = true;

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignMapper campaignMapper;

    @Autowired
    private CampaignService campaignService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restCampaignMockMvc;

    private Campaign campaign;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .name(DEFAULT_NAME)
            .code(DEFAULT_CODE)
            .status(DEFAULT_STATUS)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .metadata(DEFAULT_METADATA)
            .manipulateSymptom(DEFAULT_MANIPULATE_SYMPTOM);
        return campaign;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Campaign createUpdatedEntity(EntityManager em) {
        Campaign campaign = new Campaign()
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .metadata(UPDATED_METADATA)
            .manipulateSymptom(UPDATED_MANIPULATE_SYMPTOM);
        return campaign;
    }

    @BeforeEach
    public void initTest() {
        campaign = createEntity(em);
    }

    @Test
    @Transactional
    public void createCampaign() throws Exception {
        int databaseSizeBeforeCreate = campaignRepository.findAll().size();
        // Create the Campaign
        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);
        restCampaignMockMvc.perform(post("/api/campaigns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isCreated());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate + 1);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testCampaign.getCode()).isEqualTo(DEFAULT_CODE);
        assertThat(testCampaign.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testCampaign.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testCampaign.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testCampaign.getMetadata()).isEqualTo(DEFAULT_METADATA);
        assertThat(testCampaign.isManipulateSymptom()).isEqualTo(DEFAULT_MANIPULATE_SYMPTOM);
    }

    @Test
    @Transactional
    public void createCampaignWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = campaignRepository.findAll().size();

        // Create the Campaign with an existing ID
        campaign.setId(1L);
        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCampaignMockMvc.perform(post("/api/campaigns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = campaignRepository.findAll().size();
        // set the field null
        campaign.setCode(null);

        // Create the Campaign, which fails.
        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);


        restCampaignMockMvc.perform(post("/api/campaigns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isBadRequest());

        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllCampaigns() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get all the campaignList
        restCampaignMockMvc.perform(get("/api/campaigns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(campaign.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].code").value(hasItem(DEFAULT_CODE)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)))
            .andExpect(jsonPath("$.[*].manipulateSymptom").value(hasItem(DEFAULT_MANIPULATE_SYMPTOM.booleanValue())));
    }
    
    @Test
    @Transactional
    public void getCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        // Get the campaign
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", campaign.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(campaign.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.code").value(DEFAULT_CODE))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA))
            .andExpect(jsonPath("$.manipulateSymptom").value(DEFAULT_MANIPULATE_SYMPTOM.booleanValue()));
    }
    @Test
    @Transactional
    public void getNonExistingCampaign() throws Exception {
        // Get the campaign
        restCampaignMockMvc.perform(get("/api/campaigns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        int databaseSizeBeforeUpdate = campaignRepository.findAll().size();

        // Update the campaign
        Campaign updatedCampaign = campaignRepository.findById(campaign.getId()).get();
        // Disconnect from session so that the updates on updatedCampaign are not directly saved in db
        em.detach(updatedCampaign);
        updatedCampaign
            .name(UPDATED_NAME)
            .code(UPDATED_CODE)
            .status(UPDATED_STATUS)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .metadata(UPDATED_METADATA)
            .manipulateSymptom(UPDATED_MANIPULATE_SYMPTOM);
        CampaignDTO campaignDTO = campaignMapper.toDto(updatedCampaign);

        restCampaignMockMvc.perform(put("/api/campaigns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isOk());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
        Campaign testCampaign = campaignList.get(campaignList.size() - 1);
        assertThat(testCampaign.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testCampaign.getCode()).isEqualTo(UPDATED_CODE);
        assertThat(testCampaign.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testCampaign.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testCampaign.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testCampaign.getMetadata()).isEqualTo(UPDATED_METADATA);
        assertThat(testCampaign.isManipulateSymptom()).isEqualTo(UPDATED_MANIPULATE_SYMPTOM);
    }

    @Test
    @Transactional
    public void updateNonExistingCampaign() throws Exception {
        int databaseSizeBeforeUpdate = campaignRepository.findAll().size();

        // Create the Campaign
        CampaignDTO campaignDTO = campaignMapper.toDto(campaign);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCampaignMockMvc.perform(put("/api/campaigns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(campaignDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Campaign in the database
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCampaign() throws Exception {
        // Initialize the database
        campaignRepository.saveAndFlush(campaign);

        int databaseSizeBeforeDelete = campaignRepository.findAll().size();

        // Delete the campaign
        restCampaignMockMvc.perform(delete("/api/campaigns/{id}", campaign.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Campaign> campaignList = campaignRepository.findAll();
        assertThat(campaignList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
