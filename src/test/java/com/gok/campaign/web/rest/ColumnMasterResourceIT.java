package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.ColumnMaster;
import com.gok.campaign.repository.ColumnMasterRepository;
import com.gok.campaign.service.ColumnMasterService;
import com.gok.campaign.service.dto.ColumnMasterDTO;
import com.gok.campaign.service.mapper.ColumnMasterMapper;

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
 * Integration tests for the {@link ColumnMasterResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class ColumnMasterResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    @Autowired
    private ColumnMasterRepository columnMasterRepository;

    @Autowired
    private ColumnMasterMapper columnMasterMapper;

    @Autowired
    private ColumnMasterService columnMasterService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restColumnMasterMockMvc;

    private ColumnMaster columnMaster;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ColumnMaster createEntity(EntityManager em) {
        ColumnMaster columnMaster = new ColumnMaster()
            .name(DEFAULT_NAME);
        return columnMaster;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ColumnMaster createUpdatedEntity(EntityManager em) {
        ColumnMaster columnMaster = new ColumnMaster()
            .name(UPDATED_NAME);
        return columnMaster;
    }

    @BeforeEach
    public void initTest() {
        columnMaster = createEntity(em);
    }

    @Test
    @Transactional
    public void createColumnMaster() throws Exception {
        int databaseSizeBeforeCreate = columnMasterRepository.findAll().size();
        // Create the ColumnMaster
        ColumnMasterDTO columnMasterDTO = columnMasterMapper.toDto(columnMaster);
        restColumnMasterMockMvc.perform(post("/api/column-masters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(columnMasterDTO)))
            .andExpect(status().isCreated());

        // Validate the ColumnMaster in the database
        List<ColumnMaster> columnMasterList = columnMasterRepository.findAll();
        assertThat(columnMasterList).hasSize(databaseSizeBeforeCreate + 1);
        ColumnMaster testColumnMaster = columnMasterList.get(columnMasterList.size() - 1);
        assertThat(testColumnMaster.getName()).isEqualTo(DEFAULT_NAME);
    }

    @Test
    @Transactional
    public void createColumnMasterWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = columnMasterRepository.findAll().size();

        // Create the ColumnMaster with an existing ID
        columnMaster.setId(1L);
        ColumnMasterDTO columnMasterDTO = columnMasterMapper.toDto(columnMaster);

        // An entity with an existing ID cannot be created, so this API call must fail
        restColumnMasterMockMvc.perform(post("/api/column-masters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(columnMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ColumnMaster in the database
        List<ColumnMaster> columnMasterList = columnMasterRepository.findAll();
        assertThat(columnMasterList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllColumnMasters() throws Exception {
        // Initialize the database
        columnMasterRepository.saveAndFlush(columnMaster);

        // Get all the columnMasterList
        restColumnMasterMockMvc.perform(get("/api/column-masters?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(columnMaster.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)));
    }
    
    @Test
    @Transactional
    public void getColumnMaster() throws Exception {
        // Initialize the database
        columnMasterRepository.saveAndFlush(columnMaster);

        // Get the columnMaster
        restColumnMasterMockMvc.perform(get("/api/column-masters/{id}", columnMaster.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(columnMaster.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME));
    }
    @Test
    @Transactional
    public void getNonExistingColumnMaster() throws Exception {
        // Get the columnMaster
        restColumnMasterMockMvc.perform(get("/api/column-masters/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateColumnMaster() throws Exception {
        // Initialize the database
        columnMasterRepository.saveAndFlush(columnMaster);

        int databaseSizeBeforeUpdate = columnMasterRepository.findAll().size();

        // Update the columnMaster
        ColumnMaster updatedColumnMaster = columnMasterRepository.findById(columnMaster.getId()).get();
        // Disconnect from session so that the updates on updatedColumnMaster are not directly saved in db
        em.detach(updatedColumnMaster);
        updatedColumnMaster
            .name(UPDATED_NAME);
        ColumnMasterDTO columnMasterDTO = columnMasterMapper.toDto(updatedColumnMaster);

        restColumnMasterMockMvc.perform(put("/api/column-masters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(columnMasterDTO)))
            .andExpect(status().isOk());

        // Validate the ColumnMaster in the database
        List<ColumnMaster> columnMasterList = columnMasterRepository.findAll();
        assertThat(columnMasterList).hasSize(databaseSizeBeforeUpdate);
        ColumnMaster testColumnMaster = columnMasterList.get(columnMasterList.size() - 1);
        assertThat(testColumnMaster.getName()).isEqualTo(UPDATED_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingColumnMaster() throws Exception {
        int databaseSizeBeforeUpdate = columnMasterRepository.findAll().size();

        // Create the ColumnMaster
        ColumnMasterDTO columnMasterDTO = columnMasterMapper.toDto(columnMaster);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restColumnMasterMockMvc.perform(put("/api/column-masters").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(columnMasterDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ColumnMaster in the database
        List<ColumnMaster> columnMasterList = columnMasterRepository.findAll();
        assertThat(columnMasterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteColumnMaster() throws Exception {
        // Initialize the database
        columnMasterRepository.saveAndFlush(columnMaster);

        int databaseSizeBeforeDelete = columnMasterRepository.findAll().size();

        // Delete the columnMaster
        restColumnMasterMockMvc.perform(delete("/api/column-masters/{id}", columnMaster.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ColumnMaster> columnMasterList = columnMasterRepository.findAll();
        assertThat(columnMasterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
