package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.Essentials;
import com.gok.campaign.repository.EssentialsRepository;
import com.gok.campaign.service.EssentialsService;
import com.gok.campaign.service.dto.EssentialsDTO;
import com.gok.campaign.service.mapper.EssentialsMapper;

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

import com.gok.campaign.domain.enumeration.TaskType;
/**
 * Integration tests for the {@link EssentialsResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class EssentialsResourceIT {

    private static final String DEFAULT_ITEM = "AAAAAAAAAA";
    private static final String UPDATED_ITEM = "BBBBBBBBBB";

    private static final String DEFAULT_ITEM_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_ITEM_DESCRIPTION = "BBBBBBBBBB";

    private static final TaskType DEFAULT_TASK_TYPE = TaskType.IN;
    private static final TaskType UPDATED_TASK_TYPE = TaskType.OUT;

    private static final Integer DEFAULT_PRIORITY = 1;
    private static final Integer UPDATED_PRIORITY = 2;

    private static final Integer DEFAULT_QUANTITY = 1;
    private static final Integer UPDATED_QUANTITY = 2;

    @Autowired
    private EssentialsRepository essentialsRepository;

    @Autowired
    private EssentialsMapper essentialsMapper;

    @Autowired
    private EssentialsService essentialsService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEssentialsMockMvc;

    private Essentials essentials;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Essentials createEntity(EntityManager em) {
        Essentials essentials = new Essentials()
            .item(DEFAULT_ITEM)
            .itemDescription(DEFAULT_ITEM_DESCRIPTION)
            .taskType(DEFAULT_TASK_TYPE)
            .priority(DEFAULT_PRIORITY)
            .quantity(DEFAULT_QUANTITY);
        return essentials;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Essentials createUpdatedEntity(EntityManager em) {
        Essentials essentials = new Essentials()
            .item(UPDATED_ITEM)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .taskType(UPDATED_TASK_TYPE)
            .priority(UPDATED_PRIORITY)
            .quantity(UPDATED_QUANTITY);
        return essentials;
    }

    @BeforeEach
    public void initTest() {
        essentials = createEntity(em);
    }

    @Test
    @Transactional
    public void createEssentials() throws Exception {
        int databaseSizeBeforeCreate = essentialsRepository.findAll().size();
        // Create the Essentials
        EssentialsDTO essentialsDTO = essentialsMapper.toDto(essentials);
        restEssentialsMockMvc.perform(post("/api/essentials").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(essentialsDTO)))
            .andExpect(status().isCreated());

        // Validate the Essentials in the database
        List<Essentials> essentialsList = essentialsRepository.findAll();
        assertThat(essentialsList).hasSize(databaseSizeBeforeCreate + 1);
        Essentials testEssentials = essentialsList.get(essentialsList.size() - 1);
        assertThat(testEssentials.getItem()).isEqualTo(DEFAULT_ITEM);
        assertThat(testEssentials.getItemDescription()).isEqualTo(DEFAULT_ITEM_DESCRIPTION);
        assertThat(testEssentials.getTaskType()).isEqualTo(DEFAULT_TASK_TYPE);
        assertThat(testEssentials.getPriority()).isEqualTo(DEFAULT_PRIORITY);
        assertThat(testEssentials.getQuantity()).isEqualTo(DEFAULT_QUANTITY);
    }

    @Test
    @Transactional
    public void createEssentialsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = essentialsRepository.findAll().size();

        // Create the Essentials with an existing ID
        essentials.setId(1L);
        EssentialsDTO essentialsDTO = essentialsMapper.toDto(essentials);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEssentialsMockMvc.perform(post("/api/essentials").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(essentialsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Essentials in the database
        List<Essentials> essentialsList = essentialsRepository.findAll();
        assertThat(essentialsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEssentials() throws Exception {
        // Initialize the database
        essentialsRepository.saveAndFlush(essentials);

        // Get all the essentialsList
        restEssentialsMockMvc.perform(get("/api/essentials?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(essentials.getId().intValue())))
            .andExpect(jsonPath("$.[*].item").value(hasItem(DEFAULT_ITEM)))
            .andExpect(jsonPath("$.[*].itemDescription").value(hasItem(DEFAULT_ITEM_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].taskType").value(hasItem(DEFAULT_TASK_TYPE.toString())))
            .andExpect(jsonPath("$.[*].priority").value(hasItem(DEFAULT_PRIORITY)))
            .andExpect(jsonPath("$.[*].quantity").value(hasItem(DEFAULT_QUANTITY)));
    }
    
    @Test
    @Transactional
    public void getEssentials() throws Exception {
        // Initialize the database
        essentialsRepository.saveAndFlush(essentials);

        // Get the essentials
        restEssentialsMockMvc.perform(get("/api/essentials/{id}", essentials.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(essentials.getId().intValue()))
            .andExpect(jsonPath("$.item").value(DEFAULT_ITEM))
            .andExpect(jsonPath("$.itemDescription").value(DEFAULT_ITEM_DESCRIPTION))
            .andExpect(jsonPath("$.taskType").value(DEFAULT_TASK_TYPE.toString()))
            .andExpect(jsonPath("$.priority").value(DEFAULT_PRIORITY))
            .andExpect(jsonPath("$.quantity").value(DEFAULT_QUANTITY));
    }
    @Test
    @Transactional
    public void getNonExistingEssentials() throws Exception {
        // Get the essentials
        restEssentialsMockMvc.perform(get("/api/essentials/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEssentials() throws Exception {
        // Initialize the database
        essentialsRepository.saveAndFlush(essentials);

        int databaseSizeBeforeUpdate = essentialsRepository.findAll().size();

        // Update the essentials
        Essentials updatedEssentials = essentialsRepository.findById(essentials.getId()).get();
        // Disconnect from session so that the updates on updatedEssentials are not directly saved in db
        em.detach(updatedEssentials);
        updatedEssentials
            .item(UPDATED_ITEM)
            .itemDescription(UPDATED_ITEM_DESCRIPTION)
            .taskType(UPDATED_TASK_TYPE)
            .priority(UPDATED_PRIORITY)
            .quantity(UPDATED_QUANTITY);
        EssentialsDTO essentialsDTO = essentialsMapper.toDto(updatedEssentials);

        restEssentialsMockMvc.perform(put("/api/essentials").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(essentialsDTO)))
            .andExpect(status().isOk());

        // Validate the Essentials in the database
        List<Essentials> essentialsList = essentialsRepository.findAll();
        assertThat(essentialsList).hasSize(databaseSizeBeforeUpdate);
        Essentials testEssentials = essentialsList.get(essentialsList.size() - 1);
        assertThat(testEssentials.getItem()).isEqualTo(UPDATED_ITEM);
        assertThat(testEssentials.getItemDescription()).isEqualTo(UPDATED_ITEM_DESCRIPTION);
        assertThat(testEssentials.getTaskType()).isEqualTo(UPDATED_TASK_TYPE);
        assertThat(testEssentials.getPriority()).isEqualTo(UPDATED_PRIORITY);
        assertThat(testEssentials.getQuantity()).isEqualTo(UPDATED_QUANTITY);
    }

    @Test
    @Transactional
    public void updateNonExistingEssentials() throws Exception {
        int databaseSizeBeforeUpdate = essentialsRepository.findAll().size();

        // Create the Essentials
        EssentialsDTO essentialsDTO = essentialsMapper.toDto(essentials);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEssentialsMockMvc.perform(put("/api/essentials").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(essentialsDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Essentials in the database
        List<Essentials> essentialsList = essentialsRepository.findAll();
        assertThat(essentialsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEssentials() throws Exception {
        // Initialize the database
        essentialsRepository.saveAndFlush(essentials);

        int databaseSizeBeforeDelete = essentialsRepository.findAll().size();

        // Delete the essentials
        restEssentialsMockMvc.perform(delete("/api/essentials/{id}", essentials.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Essentials> essentialsList = essentialsRepository.findAll();
        assertThat(essentialsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
