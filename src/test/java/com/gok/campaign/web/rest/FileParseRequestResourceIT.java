package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.FileParseRequest;
import com.gok.campaign.repository.FileParseRequestRepository;
import com.gok.campaign.service.FileParseRequestService;
import com.gok.campaign.service.dto.FileParseRequestDTO;
import com.gok.campaign.service.mapper.FileParseRequestMapper;

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

import com.gok.campaign.domain.enumeration.RequestStatus;
/**
 * Integration tests for the {@link FileParseRequestResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class FileParseRequestResourceIT {

    private static final Long DEFAULT_FILE_ID = 1L;
    private static final Long UPDATED_FILE_ID = 2L;

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final RequestStatus DEFAULT_STATUS = RequestStatus.FAILURE;
    private static final RequestStatus UPDATED_STATUS = RequestStatus.SUCCESS;

    private static final String DEFAULT_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_MESSAGE = "BBBBBBBBBB";

    private static final Long DEFAULT_RECORDS = 1L;
    private static final Long UPDATED_RECORDS = 2L;

    private static final String DEFAULT_METADATA = "AAAAAAAAAA";
    private static final String UPDATED_METADATA = "BBBBBBBBBB";

    @Autowired
    private FileParseRequestRepository fileParseRequestRepository;

    @Autowired
    private FileParseRequestMapper fileParseRequestMapper;

    @Autowired
    private FileParseRequestService fileParseRequestService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFileParseRequestMockMvc;

    private FileParseRequest fileParseRequest;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileParseRequest createEntity(EntityManager em) {
        FileParseRequest fileParseRequest = new FileParseRequest()
            .fileId(DEFAULT_FILE_ID)
            .fileName(DEFAULT_FILE_NAME)
            .status(DEFAULT_STATUS)
            .message(DEFAULT_MESSAGE)
            .records(DEFAULT_RECORDS)
            .metadata(DEFAULT_METADATA);
        return fileParseRequest;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FileParseRequest createUpdatedEntity(EntityManager em) {
        FileParseRequest fileParseRequest = new FileParseRequest()
            .fileId(UPDATED_FILE_ID)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .message(UPDATED_MESSAGE)
            .records(UPDATED_RECORDS)
            .metadata(UPDATED_METADATA);
        return fileParseRequest;
    }

    @BeforeEach
    public void initTest() {
        fileParseRequest = createEntity(em);
    }

    @Test
    @Transactional
    public void createFileParseRequest() throws Exception {
        int databaseSizeBeforeCreate = fileParseRequestRepository.findAll().size();
        // Create the FileParseRequest
        FileParseRequestDTO fileParseRequestDTO = fileParseRequestMapper.toDto(fileParseRequest);
        restFileParseRequestMockMvc.perform(post("/api/file-parse-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileParseRequestDTO)))
            .andExpect(status().isCreated());

        // Validate the FileParseRequest in the database
        List<FileParseRequest> fileParseRequestList = fileParseRequestRepository.findAll();
        assertThat(fileParseRequestList).hasSize(databaseSizeBeforeCreate + 1);
        FileParseRequest testFileParseRequest = fileParseRequestList.get(fileParseRequestList.size() - 1);
        assertThat(testFileParseRequest.getFileId()).isEqualTo(DEFAULT_FILE_ID);
        assertThat(testFileParseRequest.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testFileParseRequest.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testFileParseRequest.getMessage()).isEqualTo(DEFAULT_MESSAGE);
        assertThat(testFileParseRequest.getRecords()).isEqualTo(DEFAULT_RECORDS);
        assertThat(testFileParseRequest.getMetadata()).isEqualTo(DEFAULT_METADATA);
    }

    @Test
    @Transactional
    public void createFileParseRequestWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileParseRequestRepository.findAll().size();

        // Create the FileParseRequest with an existing ID
        fileParseRequest.setId(1L);
        FileParseRequestDTO fileParseRequestDTO = fileParseRequestMapper.toDto(fileParseRequest);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileParseRequestMockMvc.perform(post("/api/file-parse-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileParseRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileParseRequest in the database
        List<FileParseRequest> fileParseRequestList = fileParseRequestRepository.findAll();
        assertThat(fileParseRequestList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFileParseRequests() throws Exception {
        // Initialize the database
        fileParseRequestRepository.saveAndFlush(fileParseRequest);

        // Get all the fileParseRequestList
        restFileParseRequestMockMvc.perform(get("/api/file-parse-requests?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fileParseRequest.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileId").value(hasItem(DEFAULT_FILE_ID.intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].message").value(hasItem(DEFAULT_MESSAGE)))
            .andExpect(jsonPath("$.[*].records").value(hasItem(DEFAULT_RECORDS.intValue())))
            .andExpect(jsonPath("$.[*].metadata").value(hasItem(DEFAULT_METADATA)));
    }
    
    @Test
    @Transactional
    public void getFileParseRequest() throws Exception {
        // Initialize the database
        fileParseRequestRepository.saveAndFlush(fileParseRequest);

        // Get the fileParseRequest
        restFileParseRequestMockMvc.perform(get("/api/file-parse-requests/{id}", fileParseRequest.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fileParseRequest.getId().intValue()))
            .andExpect(jsonPath("$.fileId").value(DEFAULT_FILE_ID.intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.message").value(DEFAULT_MESSAGE))
            .andExpect(jsonPath("$.records").value(DEFAULT_RECORDS.intValue()))
            .andExpect(jsonPath("$.metadata").value(DEFAULT_METADATA));
    }
    @Test
    @Transactional
    public void getNonExistingFileParseRequest() throws Exception {
        // Get the fileParseRequest
        restFileParseRequestMockMvc.perform(get("/api/file-parse-requests/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFileParseRequest() throws Exception {
        // Initialize the database
        fileParseRequestRepository.saveAndFlush(fileParseRequest);

        int databaseSizeBeforeUpdate = fileParseRequestRepository.findAll().size();

        // Update the fileParseRequest
        FileParseRequest updatedFileParseRequest = fileParseRequestRepository.findById(fileParseRequest.getId()).get();
        // Disconnect from session so that the updates on updatedFileParseRequest are not directly saved in db
        em.detach(updatedFileParseRequest);
        updatedFileParseRequest
            .fileId(UPDATED_FILE_ID)
            .fileName(UPDATED_FILE_NAME)
            .status(UPDATED_STATUS)
            .message(UPDATED_MESSAGE)
            .records(UPDATED_RECORDS)
            .metadata(UPDATED_METADATA);
        FileParseRequestDTO fileParseRequestDTO = fileParseRequestMapper.toDto(updatedFileParseRequest);

        restFileParseRequestMockMvc.perform(put("/api/file-parse-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileParseRequestDTO)))
            .andExpect(status().isOk());

        // Validate the FileParseRequest in the database
        List<FileParseRequest> fileParseRequestList = fileParseRequestRepository.findAll();
        assertThat(fileParseRequestList).hasSize(databaseSizeBeforeUpdate);
        FileParseRequest testFileParseRequest = fileParseRequestList.get(fileParseRequestList.size() - 1);
        assertThat(testFileParseRequest.getFileId()).isEqualTo(UPDATED_FILE_ID);
        assertThat(testFileParseRequest.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testFileParseRequest.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testFileParseRequest.getMessage()).isEqualTo(UPDATED_MESSAGE);
        assertThat(testFileParseRequest.getRecords()).isEqualTo(UPDATED_RECORDS);
        assertThat(testFileParseRequest.getMetadata()).isEqualTo(UPDATED_METADATA);
    }

    @Test
    @Transactional
    public void updateNonExistingFileParseRequest() throws Exception {
        int databaseSizeBeforeUpdate = fileParseRequestRepository.findAll().size();

        // Create the FileParseRequest
        FileParseRequestDTO fileParseRequestDTO = fileParseRequestMapper.toDto(fileParseRequest);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileParseRequestMockMvc.perform(put("/api/file-parse-requests").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileParseRequestDTO)))
            .andExpect(status().isBadRequest());

        // Validate the FileParseRequest in the database
        List<FileParseRequest> fileParseRequestList = fileParseRequestRepository.findAll();
        assertThat(fileParseRequestList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFileParseRequest() throws Exception {
        // Initialize the database
        fileParseRequestRepository.saveAndFlush(fileParseRequest);

        int databaseSizeBeforeDelete = fileParseRequestRepository.findAll().size();

        // Delete the fileParseRequest
        restFileParseRequestMockMvc.perform(delete("/api/file-parse-requests/{id}", fileParseRequest.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FileParseRequest> fileParseRequestList = fileParseRequestRepository.findAll();
        assertThat(fileParseRequestList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
