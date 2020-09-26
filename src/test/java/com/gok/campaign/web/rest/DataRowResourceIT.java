package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.DataRow;
import com.gok.campaign.repository.DataRowRepository;
import com.gok.campaign.service.DataRowService;
import com.gok.campaign.service.dto.DataRowDTO;
import com.gok.campaign.service.mapper.DataRowMapper;

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
 * Integration tests for the {@link DataRowResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class DataRowResourceIT {

    private static final String DEFAULT_S_NO = "AAAAAAAAAA";
    private static final String UPDATED_S_NO = "BBBBBBBBBB";

    private static final String DEFAULT_IDENTIFIER = "AAAAAAAAAA";
    private static final String UPDATED_IDENTIFIER = "BBBBBBBBBB";

    private static final String DEFAULT_SOURCE = "AAAAAAAAAA";
    private static final String UPDATED_SOURCE = "BBBBBBBBBB";

    @Autowired
    private DataRowRepository dataRowRepository;

    @Autowired
    private DataRowMapper dataRowMapper;

    @Autowired
    private DataRowService dataRowService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDataRowMockMvc;

    private DataRow dataRow;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataRow createEntity(EntityManager em) {
        DataRow dataRow = new DataRow()
            .sNo(DEFAULT_S_NO)
            .identifier(DEFAULT_IDENTIFIER)
            .source(DEFAULT_SOURCE);
        return dataRow;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DataRow createUpdatedEntity(EntityManager em) {
        DataRow dataRow = new DataRow()
            .sNo(UPDATED_S_NO)
            .identifier(UPDATED_IDENTIFIER)
            .source(UPDATED_SOURCE);
        return dataRow;
    }

    @BeforeEach
    public void initTest() {
        dataRow = createEntity(em);
    }

    @Test
    @Transactional
    public void createDataRow() throws Exception {
        int databaseSizeBeforeCreate = dataRowRepository.findAll().size();
        // Create the DataRow
        DataRowDTO dataRowDTO = dataRowMapper.toDto(dataRow);
        restDataRowMockMvc.perform(post("/api/data-rows").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRowDTO)))
            .andExpect(status().isCreated());

        // Validate the DataRow in the database
        List<DataRow> dataRowList = dataRowRepository.findAll();
        assertThat(dataRowList).hasSize(databaseSizeBeforeCreate + 1);
        DataRow testDataRow = dataRowList.get(dataRowList.size() - 1);
        assertThat(testDataRow.getsNo()).isEqualTo(DEFAULT_S_NO);
        assertThat(testDataRow.getIdentifier()).isEqualTo(DEFAULT_IDENTIFIER);
        assertThat(testDataRow.getSource()).isEqualTo(DEFAULT_SOURCE);
    }

    @Test
    @Transactional
    public void createDataRowWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = dataRowRepository.findAll().size();

        // Create the DataRow with an existing ID
        dataRow.setId(1L);
        DataRowDTO dataRowDTO = dataRowMapper.toDto(dataRow);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDataRowMockMvc.perform(post("/api/data-rows").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataRow in the database
        List<DataRow> dataRowList = dataRowRepository.findAll();
        assertThat(dataRowList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDataRows() throws Exception {
        // Initialize the database
        dataRowRepository.saveAndFlush(dataRow);

        // Get all the dataRowList
        restDataRowMockMvc.perform(get("/api/data-rows?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(dataRow.getId().intValue())))
            .andExpect(jsonPath("$.[*].sNo").value(hasItem(DEFAULT_S_NO)))
            .andExpect(jsonPath("$.[*].identifier").value(hasItem(DEFAULT_IDENTIFIER)))
            .andExpect(jsonPath("$.[*].source").value(hasItem(DEFAULT_SOURCE)));
    }
    
    @Test
    @Transactional
    public void getDataRow() throws Exception {
        // Initialize the database
        dataRowRepository.saveAndFlush(dataRow);

        // Get the dataRow
        restDataRowMockMvc.perform(get("/api/data-rows/{id}", dataRow.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(dataRow.getId().intValue()))
            .andExpect(jsonPath("$.sNo").value(DEFAULT_S_NO))
            .andExpect(jsonPath("$.identifier").value(DEFAULT_IDENTIFIER))
            .andExpect(jsonPath("$.source").value(DEFAULT_SOURCE));
    }
    @Test
    @Transactional
    public void getNonExistingDataRow() throws Exception {
        // Get the dataRow
        restDataRowMockMvc.perform(get("/api/data-rows/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDataRow() throws Exception {
        // Initialize the database
        dataRowRepository.saveAndFlush(dataRow);

        int databaseSizeBeforeUpdate = dataRowRepository.findAll().size();

        // Update the dataRow
        DataRow updatedDataRow = dataRowRepository.findById(dataRow.getId()).get();
        // Disconnect from session so that the updates on updatedDataRow are not directly saved in db
        em.detach(updatedDataRow);
        updatedDataRow
            .sNo(UPDATED_S_NO)
            .identifier(UPDATED_IDENTIFIER)
            .source(UPDATED_SOURCE);
        DataRowDTO dataRowDTO = dataRowMapper.toDto(updatedDataRow);

        restDataRowMockMvc.perform(put("/api/data-rows").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRowDTO)))
            .andExpect(status().isOk());

        // Validate the DataRow in the database
        List<DataRow> dataRowList = dataRowRepository.findAll();
        assertThat(dataRowList).hasSize(databaseSizeBeforeUpdate);
        DataRow testDataRow = dataRowList.get(dataRowList.size() - 1);
        assertThat(testDataRow.getsNo()).isEqualTo(UPDATED_S_NO);
        assertThat(testDataRow.getIdentifier()).isEqualTo(UPDATED_IDENTIFIER);
        assertThat(testDataRow.getSource()).isEqualTo(UPDATED_SOURCE);
    }

    @Test
    @Transactional
    public void updateNonExistingDataRow() throws Exception {
        int databaseSizeBeforeUpdate = dataRowRepository.findAll().size();

        // Create the DataRow
        DataRowDTO dataRowDTO = dataRowMapper.toDto(dataRow);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDataRowMockMvc.perform(put("/api/data-rows").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(dataRowDTO)))
            .andExpect(status().isBadRequest());

        // Validate the DataRow in the database
        List<DataRow> dataRowList = dataRowRepository.findAll();
        assertThat(dataRowList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDataRow() throws Exception {
        // Initialize the database
        dataRowRepository.saveAndFlush(dataRow);

        int databaseSizeBeforeDelete = dataRowRepository.findAll().size();

        // Delete the dataRow
        restDataRowMockMvc.perform(delete("/api/data-rows/{id}", dataRow.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<DataRow> dataRowList = dataRowRepository.findAll();
        assertThat(dataRowList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
