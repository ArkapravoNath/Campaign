package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.ServiceReq;
import com.gok.campaign.repository.ServiceReqRepository;
import com.gok.campaign.service.ServiceReqService;
import com.gok.campaign.service.dto.ServiceReqDTO;
import com.gok.campaign.service.mapper.ServiceReqMapper;

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

import com.gok.campaign.domain.enumeration.ServiceType;
import com.gok.campaign.domain.enumeration.ServiceStatus;
import com.gok.campaign.domain.enumeration.ServiceSubStatus;
/**
 * Integration tests for the {@link ServiceReqResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class ServiceReqResourceIT {

    private static final ServiceType DEFAULT_SERVICE_TYPE = ServiceType.MEDICAL;
    private static final ServiceType UPDATED_SERVICE_TYPE = ServiceType.FOOD;

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final ServiceStatus DEFAULT_STATUS = ServiceStatus.NEW;
    private static final ServiceStatus UPDATED_STATUS = ServiceStatus.PENDING;

    private static final String DEFAULT_STATUS_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_STATUS_DESCRIPTION = "BBBBBBBBBB";

    private static final ServiceSubStatus DEFAULT_SUB_STATUS = ServiceSubStatus.INITIATED;
    private static final ServiceSubStatus UPDATED_SUB_STATUS = ServiceSubStatus.COMPLETED;

    private static final String DEFAULT_REMARKS = "AAAAAAAAAA";
    private static final String UPDATED_REMARKS = "BBBBBBBBBB";

    @Autowired
    private ServiceReqRepository serviceReqRepository;

    @Autowired
    private ServiceReqMapper serviceReqMapper;

    @Autowired
    private ServiceReqService serviceReqService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restServiceReqMockMvc;

    private ServiceReq serviceReq;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceReq createEntity(EntityManager em) {
        ServiceReq serviceReq = new ServiceReq()
            .serviceType(DEFAULT_SERVICE_TYPE)
            .name(DEFAULT_NAME)
            .description(DEFAULT_DESCRIPTION)
            .status(DEFAULT_STATUS)
            .statusDescription(DEFAULT_STATUS_DESCRIPTION)
            .subStatus(DEFAULT_SUB_STATUS)
            .remarks(DEFAULT_REMARKS);
        return serviceReq;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ServiceReq createUpdatedEntity(EntityManager em) {
        ServiceReq serviceReq = new ServiceReq()
            .serviceType(UPDATED_SERVICE_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .statusDescription(UPDATED_STATUS_DESCRIPTION)
            .subStatus(UPDATED_SUB_STATUS)
            .remarks(UPDATED_REMARKS);
        return serviceReq;
    }

    @BeforeEach
    public void initTest() {
        serviceReq = createEntity(em);
    }

    @Test
    @Transactional
    public void createServiceReq() throws Exception {
        int databaseSizeBeforeCreate = serviceReqRepository.findAll().size();
        // Create the ServiceReq
        ServiceReqDTO serviceReqDTO = serviceReqMapper.toDto(serviceReq);
        restServiceReqMockMvc.perform(post("/api/service-reqs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceReqDTO)))
            .andExpect(status().isCreated());

        // Validate the ServiceReq in the database
        List<ServiceReq> serviceReqList = serviceReqRepository.findAll();
        assertThat(serviceReqList).hasSize(databaseSizeBeforeCreate + 1);
        ServiceReq testServiceReq = serviceReqList.get(serviceReqList.size() - 1);
        assertThat(testServiceReq.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testServiceReq.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testServiceReq.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testServiceReq.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testServiceReq.getStatusDescription()).isEqualTo(DEFAULT_STATUS_DESCRIPTION);
        assertThat(testServiceReq.getSubStatus()).isEqualTo(DEFAULT_SUB_STATUS);
        assertThat(testServiceReq.getRemarks()).isEqualTo(DEFAULT_REMARKS);
    }

    @Test
    @Transactional
    public void createServiceReqWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = serviceReqRepository.findAll().size();

        // Create the ServiceReq with an existing ID
        serviceReq.setId(1L);
        ServiceReqDTO serviceReqDTO = serviceReqMapper.toDto(serviceReq);

        // An entity with an existing ID cannot be created, so this API call must fail
        restServiceReqMockMvc.perform(post("/api/service-reqs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceReqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceReq in the database
        List<ServiceReq> serviceReqList = serviceReqRepository.findAll();
        assertThat(serviceReqList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllServiceReqs() throws Exception {
        // Initialize the database
        serviceReqRepository.saveAndFlush(serviceReq);

        // Get all the serviceReqList
        restServiceReqMockMvc.perform(get("/api/service-reqs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(serviceReq.getId().intValue())))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].statusDescription").value(hasItem(DEFAULT_STATUS_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].subStatus").value(hasItem(DEFAULT_SUB_STATUS.toString())))
            .andExpect(jsonPath("$.[*].remarks").value(hasItem(DEFAULT_REMARKS)));
    }
    
    @Test
    @Transactional
    public void getServiceReq() throws Exception {
        // Initialize the database
        serviceReqRepository.saveAndFlush(serviceReq);

        // Get the serviceReq
        restServiceReqMockMvc.perform(get("/api/service-reqs/{id}", serviceReq.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(serviceReq.getId().intValue()))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.statusDescription").value(DEFAULT_STATUS_DESCRIPTION))
            .andExpect(jsonPath("$.subStatus").value(DEFAULT_SUB_STATUS.toString()))
            .andExpect(jsonPath("$.remarks").value(DEFAULT_REMARKS));
    }
    @Test
    @Transactional
    public void getNonExistingServiceReq() throws Exception {
        // Get the serviceReq
        restServiceReqMockMvc.perform(get("/api/service-reqs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateServiceReq() throws Exception {
        // Initialize the database
        serviceReqRepository.saveAndFlush(serviceReq);

        int databaseSizeBeforeUpdate = serviceReqRepository.findAll().size();

        // Update the serviceReq
        ServiceReq updatedServiceReq = serviceReqRepository.findById(serviceReq.getId()).get();
        // Disconnect from session so that the updates on updatedServiceReq are not directly saved in db
        em.detach(updatedServiceReq);
        updatedServiceReq
            .serviceType(UPDATED_SERVICE_TYPE)
            .name(UPDATED_NAME)
            .description(UPDATED_DESCRIPTION)
            .status(UPDATED_STATUS)
            .statusDescription(UPDATED_STATUS_DESCRIPTION)
            .subStatus(UPDATED_SUB_STATUS)
            .remarks(UPDATED_REMARKS);
        ServiceReqDTO serviceReqDTO = serviceReqMapper.toDto(updatedServiceReq);

        restServiceReqMockMvc.perform(put("/api/service-reqs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceReqDTO)))
            .andExpect(status().isOk());

        // Validate the ServiceReq in the database
        List<ServiceReq> serviceReqList = serviceReqRepository.findAll();
        assertThat(serviceReqList).hasSize(databaseSizeBeforeUpdate);
        ServiceReq testServiceReq = serviceReqList.get(serviceReqList.size() - 1);
        assertThat(testServiceReq.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testServiceReq.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testServiceReq.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testServiceReq.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testServiceReq.getStatusDescription()).isEqualTo(UPDATED_STATUS_DESCRIPTION);
        assertThat(testServiceReq.getSubStatus()).isEqualTo(UPDATED_SUB_STATUS);
        assertThat(testServiceReq.getRemarks()).isEqualTo(UPDATED_REMARKS);
    }

    @Test
    @Transactional
    public void updateNonExistingServiceReq() throws Exception {
        int databaseSizeBeforeUpdate = serviceReqRepository.findAll().size();

        // Create the ServiceReq
        ServiceReqDTO serviceReqDTO = serviceReqMapper.toDto(serviceReq);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restServiceReqMockMvc.perform(put("/api/service-reqs").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(serviceReqDTO)))
            .andExpect(status().isBadRequest());

        // Validate the ServiceReq in the database
        List<ServiceReq> serviceReqList = serviceReqRepository.findAll();
        assertThat(serviceReqList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteServiceReq() throws Exception {
        // Initialize the database
        serviceReqRepository.saveAndFlush(serviceReq);

        int databaseSizeBeforeDelete = serviceReqRepository.findAll().size();

        // Delete the serviceReq
        restServiceReqMockMvc.perform(delete("/api/service-reqs/{id}", serviceReq.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ServiceReq> serviceReqList = serviceReqRepository.findAll();
        assertThat(serviceReqList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
