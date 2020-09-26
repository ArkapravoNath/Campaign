package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.File;
import com.gok.campaign.repository.FileRepository;
import com.gok.campaign.service.FileService;
import com.gok.campaign.service.dto.FileDTO;
import com.gok.campaign.service.mapper.FileMapper;

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
 * Integration tests for the {@link FileResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class FileResourceIT {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final Float DEFAULT_SIZE = 1F;
    private static final Float UPDATED_SIZE = 2F;

    private static final String DEFAULT_FILE_ORIGINAL_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_ORIGINAL_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_LOCATION = "AAAAAAAAAA";
    private static final String UPDATED_FILE_LOCATION = "BBBBBBBBBB";

    private static final String DEFAULT_ANTIVIRUS_CHECK = "AAAAAAAAAA";
    private static final String UPDATED_ANTIVIRUS_CHECK = "BBBBBBBBBB";

    private static final String DEFAULT_CONTENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_CONTENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_CAMPAIGN_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CAMPAIGN_CODE = "BBBBBBBBBB";

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private FileService fileService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFileMockMvc;

    private File file;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static File createEntity(EntityManager em) {
        File file = new File()
            .fileName(DEFAULT_FILE_NAME)
            .type(DEFAULT_TYPE)
            .size(DEFAULT_SIZE)
            .fileOriginalName(DEFAULT_FILE_ORIGINAL_NAME)
            .fileLocation(DEFAULT_FILE_LOCATION)
            .antivirusCheck(DEFAULT_ANTIVIRUS_CHECK)
            .contentType(DEFAULT_CONTENT_TYPE)
            .campaignCode(DEFAULT_CAMPAIGN_CODE);
        return file;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static File createUpdatedEntity(EntityManager em) {
        File file = new File()
            .fileName(UPDATED_FILE_NAME)
            .type(UPDATED_TYPE)
            .size(UPDATED_SIZE)
            .fileOriginalName(UPDATED_FILE_ORIGINAL_NAME)
            .fileLocation(UPDATED_FILE_LOCATION)
            .antivirusCheck(UPDATED_ANTIVIRUS_CHECK)
            .contentType(UPDATED_CONTENT_TYPE)
            .campaignCode(UPDATED_CAMPAIGN_CODE);
        return file;
    }

    @BeforeEach
    public void initTest() {
        file = createEntity(em);
    }

    @Test
    @Transactional
    public void createFile() throws Exception {
        int databaseSizeBeforeCreate = fileRepository.findAll().size();
        // Create the File
        FileDTO fileDTO = fileMapper.toDto(file);
        restFileMockMvc.perform(post("/api/files").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileDTO)))
            .andExpect(status().isCreated());

        // Validate the File in the database
        List<File> fileList = fileRepository.findAll();
        assertThat(fileList).hasSize(databaseSizeBeforeCreate + 1);
        File testFile = fileList.get(fileList.size() - 1);
        assertThat(testFile.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testFile.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testFile.getSize()).isEqualTo(DEFAULT_SIZE);
        assertThat(testFile.getFileOriginalName()).isEqualTo(DEFAULT_FILE_ORIGINAL_NAME);
        assertThat(testFile.getFileLocation()).isEqualTo(DEFAULT_FILE_LOCATION);
        assertThat(testFile.getAntivirusCheck()).isEqualTo(DEFAULT_ANTIVIRUS_CHECK);
        assertThat(testFile.getContentType()).isEqualTo(DEFAULT_CONTENT_TYPE);
        assertThat(testFile.getCampaignCode()).isEqualTo(DEFAULT_CAMPAIGN_CODE);
    }

    @Test
    @Transactional
    public void createFileWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fileRepository.findAll().size();

        // Create the File with an existing ID
        file.setId(1L);
        FileDTO fileDTO = fileMapper.toDto(file);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFileMockMvc.perform(post("/api/files").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the File in the database
        List<File> fileList = fileRepository.findAll();
        assertThat(fileList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFileNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = fileRepository.findAll().size();
        // set the field null
        file.setFileName(null);

        // Create the File, which fails.
        FileDTO fileDTO = fileMapper.toDto(file);


        restFileMockMvc.perform(post("/api/files").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileDTO)))
            .andExpect(status().isBadRequest());

        List<File> fileList = fileRepository.findAll();
        assertThat(fileList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFiles() throws Exception {
        // Initialize the database
        fileRepository.saveAndFlush(file);

        // Get all the fileList
        restFileMockMvc.perform(get("/api/files?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(file.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].size").value(hasItem(DEFAULT_SIZE.doubleValue())))
            .andExpect(jsonPath("$.[*].fileOriginalName").value(hasItem(DEFAULT_FILE_ORIGINAL_NAME)))
            .andExpect(jsonPath("$.[*].fileLocation").value(hasItem(DEFAULT_FILE_LOCATION)))
            .andExpect(jsonPath("$.[*].antivirusCheck").value(hasItem(DEFAULT_ANTIVIRUS_CHECK)))
            .andExpect(jsonPath("$.[*].contentType").value(hasItem(DEFAULT_CONTENT_TYPE)))
            .andExpect(jsonPath("$.[*].campaignCode").value(hasItem(DEFAULT_CAMPAIGN_CODE)));
    }
    
    @Test
    @Transactional
    public void getFile() throws Exception {
        // Initialize the database
        fileRepository.saveAndFlush(file);

        // Get the file
        restFileMockMvc.perform(get("/api/files/{id}", file.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(file.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.size").value(DEFAULT_SIZE.doubleValue()))
            .andExpect(jsonPath("$.fileOriginalName").value(DEFAULT_FILE_ORIGINAL_NAME))
            .andExpect(jsonPath("$.fileLocation").value(DEFAULT_FILE_LOCATION))
            .andExpect(jsonPath("$.antivirusCheck").value(DEFAULT_ANTIVIRUS_CHECK))
            .andExpect(jsonPath("$.contentType").value(DEFAULT_CONTENT_TYPE))
            .andExpect(jsonPath("$.campaignCode").value(DEFAULT_CAMPAIGN_CODE));
    }
    @Test
    @Transactional
    public void getNonExistingFile() throws Exception {
        // Get the file
        restFileMockMvc.perform(get("/api/files/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFile() throws Exception {
        // Initialize the database
        fileRepository.saveAndFlush(file);

        int databaseSizeBeforeUpdate = fileRepository.findAll().size();

        // Update the file
        File updatedFile = fileRepository.findById(file.getId()).get();
        // Disconnect from session so that the updates on updatedFile are not directly saved in db
        em.detach(updatedFile);
        updatedFile
            .fileName(UPDATED_FILE_NAME)
            .type(UPDATED_TYPE)
            .size(UPDATED_SIZE)
            .fileOriginalName(UPDATED_FILE_ORIGINAL_NAME)
            .fileLocation(UPDATED_FILE_LOCATION)
            .antivirusCheck(UPDATED_ANTIVIRUS_CHECK)
            .contentType(UPDATED_CONTENT_TYPE)
            .campaignCode(UPDATED_CAMPAIGN_CODE);
        FileDTO fileDTO = fileMapper.toDto(updatedFile);

        restFileMockMvc.perform(put("/api/files").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileDTO)))
            .andExpect(status().isOk());

        // Validate the File in the database
        List<File> fileList = fileRepository.findAll();
        assertThat(fileList).hasSize(databaseSizeBeforeUpdate);
        File testFile = fileList.get(fileList.size() - 1);
        assertThat(testFile.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testFile.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testFile.getSize()).isEqualTo(UPDATED_SIZE);
        assertThat(testFile.getFileOriginalName()).isEqualTo(UPDATED_FILE_ORIGINAL_NAME);
        assertThat(testFile.getFileLocation()).isEqualTo(UPDATED_FILE_LOCATION);
        assertThat(testFile.getAntivirusCheck()).isEqualTo(UPDATED_ANTIVIRUS_CHECK);
        assertThat(testFile.getContentType()).isEqualTo(UPDATED_CONTENT_TYPE);
        assertThat(testFile.getCampaignCode()).isEqualTo(UPDATED_CAMPAIGN_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingFile() throws Exception {
        int databaseSizeBeforeUpdate = fileRepository.findAll().size();

        // Create the File
        FileDTO fileDTO = fileMapper.toDto(file);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFileMockMvc.perform(put("/api/files").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fileDTO)))
            .andExpect(status().isBadRequest());

        // Validate the File in the database
        List<File> fileList = fileRepository.findAll();
        assertThat(fileList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFile() throws Exception {
        // Initialize the database
        fileRepository.saveAndFlush(file);

        int databaseSizeBeforeDelete = fileRepository.findAll().size();

        // Delete the file
        restFileMockMvc.perform(delete("/api/files/{id}", file.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<File> fileList = fileRepository.findAll();
        assertThat(fileList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
