package com.gok.campaign.web.rest;

import com.gok.campaign.CampaignApp;
import com.gok.campaign.config.TestSecurityConfiguration;
import com.gok.campaign.domain.Question;
import com.gok.campaign.repository.QuestionRepository;
import com.gok.campaign.service.QuestionService;
import com.gok.campaign.service.dto.QuestionDTO;
import com.gok.campaign.service.mapper.QuestionMapper;

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

import com.gok.campaign.domain.enumeration.StatusType;
/**
 * Integration tests for the {@link QuestionResource} REST controller.
 */
@SpringBootTest(classes = { CampaignApp.class, TestSecurityConfiguration.class })
@AutoConfigureMockMvc
@WithMockUser
public class QuestionResourceIT {

    private static final String DEFAULT_TEXT = "AAAAAAAAAA";
    private static final String UPDATED_TEXT = "BBBBBBBBBB";

    private static final String DEFAULT_INPUT_CONTROL = "AAAAAAAAAA";
    private static final String UPDATED_INPUT_CONTROL = "BBBBBBBBBB";

    private static final String DEFAULT_MASTER_VALUES = "AAAAAAAAAA";
    private static final String UPDATED_MASTER_VALUES = "BBBBBBBBBB";

    private static final StatusType DEFAULT_STATUS = StatusType.ACTIVE;
    private static final StatusType UPDATED_STATUS = StatusType.INACTIVE;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restQuestionMockMvc;

    private Question question;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createEntity(EntityManager em) {
        Question question = new Question()
            .text(DEFAULT_TEXT)
            .inputControl(DEFAULT_INPUT_CONTROL)
            .masterValues(DEFAULT_MASTER_VALUES)
            .status(DEFAULT_STATUS);
        return question;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Question createUpdatedEntity(EntityManager em) {
        Question question = new Question()
            .text(UPDATED_TEXT)
            .inputControl(UPDATED_INPUT_CONTROL)
            .masterValues(UPDATED_MASTER_VALUES)
            .status(UPDATED_STATUS);
        return question;
    }

    @BeforeEach
    public void initTest() {
        question = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuestion() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();
        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);
        restQuestionMockMvc.perform(post("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isCreated());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate + 1);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getText()).isEqualTo(DEFAULT_TEXT);
        assertThat(testQuestion.getInputControl()).isEqualTo(DEFAULT_INPUT_CONTROL);
        assertThat(testQuestion.getMasterValues()).isEqualTo(DEFAULT_MASTER_VALUES);
        assertThat(testQuestion.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createQuestionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = questionRepository.findAll().size();

        // Create the Question with an existing ID
        question.setId(1L);
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuestionMockMvc.perform(post("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllQuestions() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get all the questionList
        restQuestionMockMvc.perform(get("/api/questions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(question.getId().intValue())))
            .andExpect(jsonPath("$.[*].text").value(hasItem(DEFAULT_TEXT)))
            .andExpect(jsonPath("$.[*].inputControl").value(hasItem(DEFAULT_INPUT_CONTROL)))
            .andExpect(jsonPath("$.[*].masterValues").value(hasItem(DEFAULT_MASTER_VALUES)))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", question.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(question.getId().intValue()))
            .andExpect(jsonPath("$.text").value(DEFAULT_TEXT))
            .andExpect(jsonPath("$.inputControl").value(DEFAULT_INPUT_CONTROL))
            .andExpect(jsonPath("$.masterValues").value(DEFAULT_MASTER_VALUES))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingQuestion() throws Exception {
        // Get the question
        restQuestionMockMvc.perform(get("/api/questions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Update the question
        Question updatedQuestion = questionRepository.findById(question.getId()).get();
        // Disconnect from session so that the updates on updatedQuestion are not directly saved in db
        em.detach(updatedQuestion);
        updatedQuestion
            .text(UPDATED_TEXT)
            .inputControl(UPDATED_INPUT_CONTROL)
            .masterValues(UPDATED_MASTER_VALUES)
            .status(UPDATED_STATUS);
        QuestionDTO questionDTO = questionMapper.toDto(updatedQuestion);

        restQuestionMockMvc.perform(put("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isOk());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
        Question testQuestion = questionList.get(questionList.size() - 1);
        assertThat(testQuestion.getText()).isEqualTo(UPDATED_TEXT);
        assertThat(testQuestion.getInputControl()).isEqualTo(UPDATED_INPUT_CONTROL);
        assertThat(testQuestion.getMasterValues()).isEqualTo(UPDATED_MASTER_VALUES);
        assertThat(testQuestion.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingQuestion() throws Exception {
        int databaseSizeBeforeUpdate = questionRepository.findAll().size();

        // Create the Question
        QuestionDTO questionDTO = questionMapper.toDto(question);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restQuestionMockMvc.perform(put("/api/questions").with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(questionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Question in the database
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteQuestion() throws Exception {
        // Initialize the database
        questionRepository.saveAndFlush(question);

        int databaseSizeBeforeDelete = questionRepository.findAll().size();

        // Delete the question
        restQuestionMockMvc.perform(delete("/api/questions/{id}", question.getId()).with(csrf())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Question> questionList = questionRepository.findAll();
        assertThat(questionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
