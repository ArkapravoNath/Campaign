package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.DataColumn;
import com.gok.campaign.repository.DataColumnRepository;
import com.gok.campaign.service.DataColumnService;
import com.gok.campaign.service.dto.DataColumnDTO;
import com.gok.campaign.service.mapper.DataColumnMapper;

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
 * Integration tests for the {@link DataColumnResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class DataColumnResourceIT {

    private static final String DEFAULT_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_VALUE = "BBBBBBBBBB";

    @Autowired
    private DataColumnRepository dataColumnRepository;

    @Autowired
    private DataColumnMapper dataColumnMapper;

    @Autowired
    private DataColumnService dataColumnService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDataColumnMockMvc;

    private DataColumn dataColumn;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataColumn createEntity(EntityManager em) {
        DataColumn dataColumn = new DataColumn()
            .value(DEFAULT_VALUE);
        return dataColumn;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataColumn createUpdatedEntity(EntityManager em) {
        DataColumn dataColumn = new DataColumn()
            .value(UPDATED_VALUE);
        return dataColumn;
    }

    @BeforeEach
    public void initTest() {
        dataColumn = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataColumn() throws Exception {
        int databaseSizeBeforeCreate = dataColumnRepository.findAll().size();
        // Create the DataColumn
        DataColumnDTO dataColumnDTO = dataColumnMapper.toDto(dataColumn);
        restDataColumnMockMvc.perform(post("/api/data-columns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataColumnDTO)))
            .andExpect(status().isCreated());

        // Validate the DataColumn in the database
        List<DataColumn> dataColumnList = dataColumnRepository.findAll();
        assertThat(dataColumnList).hasSize(databaseSizeBeforeCreate + 1);
        DataColumn testDataColumn = dataColumnList.get(dataColumnList.size() - 1);
        assertThat(testDataColumn.getValue()).isEqualTo(DEFAULT_VALUE);
    }

    @Test
    @Transactional
    public void createDataColumnWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataColumnRepository.findAll().size();

        // Create the DataColumn with an existing ID
        dataColumn.setId(1L);
        DataColumnDTO dataColumnDTO = dataColumnMapper.toDto(dataColumn);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataColumnMockMvc.perform(post("/api/data-columns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataColumnDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataColumn in the database
        List<DataColumn> dataColumnList = dataColumnRepository.findAll();
        assertThat(dataColumnList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataColumns() throws Exception {
        // Initialize the database
        dataColumnRepository.saveAndFlush(dataColumn);

        // Get all the dataColumnList
        restDataColumnMockMvc.perform(get("/api/data-columns?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataColumn.getId().intValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE)));
    }
    
    @Test
    @Transactional
    public void getDataColumn() throws Exception {
        // Initialize the database
        dataColumnRepository.saveAndFlush(dataColumn);

        // Get the dataColumn
        restDataColumnMockMvc.perform(get("/api/data-columns/{id}", dataColumn.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataColumn.getId().intValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE));
    }
    @Test
    @Transactional
    public void getNonExistingDataColumn() throws Exception {
        // Get the dataColumn
        restDataColumnMockMvc.perform(get("/api/data-columns/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataColumn() throws Exception {
        // Initialize the database
        dataColumnRepository.saveAndFlush(dataColumn);

        int databaseSizeBeforeUpdate = dataColumnRepository.findAll().size();

        // Update the dataColumn
        DataColumn updatedDataColumn = dataColumnRepository.findById(dataColumn.getId()).get();
        // Disconnect from session so that the updates on updatedDataColumn are not directly saved in db
        em.detach(updatedDataColumn);
        updatedDataColumn
            .value(UPDATED_VALUE);
        DataColumnDTO dataColumnDTO = dataColumnMapper.toDto(updatedDataColumn);

        restDataColumnMockMvc.perform(put("/api/data-columns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataColumnDTO)))
            .andExpect(status().isOk());

        // Validate the DataColumn in the database
        List<DataColumn> dataColumnList = dataColumnRepository.findAll();
        assertThat(dataColumnList).hasSize(databaseSizeBeforeUpdate);
        DataColumn testDataColumn = dataColumnList.get(dataColumnList.size() - 1);
        assertThat(testDataColumn.getValue()).isEqualTo(UPDATED_VALUE);
    }

    @Test
    @Transactional
    public void updateNonExistingDataColumn() throws Exception {
        int databaseSizeBeforeUpdate = dataColumnRepository.findAll().size();

        // Create the DataColumn
        DataColumnDTO dataColumnDTO = dataColumnMapper.toDto(dataColumn);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataColumnMockMvc.perform(put("/api/data-columns").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataColumnDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataColumn in the database
        List<DataColumn> dataColumnList = dataColumnRepository.findAll();
        assertThat(dataColumnList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataColumn() throws Exception {
        // Initialize the database
        dataColumnRepository.saveAndFlush(dataColumn);

        int databaseSizeBeforeDelete = dataColumnRepository.findAll().size();

        // Delete the dataColumn
        restDataColumnMockMvc.perform(delete("/api/data-columns/{id}", dataColumn.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataColumn> dataColumnList = dataColumnRepository.findAll();
        assertThat(dataColumnList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
