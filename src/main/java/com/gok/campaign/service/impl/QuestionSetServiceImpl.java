package com.gok.campaign.service.impl;

import com.gok.campaign.service.QuestionSetService;
import com.gok.campaign.domain.QuestionSet;
import com.gok.campaign.repository.QuestionSetRepository;
import com.gok.campaign.service.dto.QuestionSetDTO;
import com.gok.campaign.service.mapper.QuestionSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link QuestionSet}.
 */
@Service
@Transactional
public class QuestionSetServiceImpl implements QuestionSetService {

    private final Logger log = LoggerFactory.getLogger(QuestionSetServiceImpl.class);

    private final QuestionSetRepository questionSetRepository;

    private final QuestionSetMapper questionSetMapper;

    public QuestionSetServiceImpl(QuestionSetRepository questionSetRepository, QuestionSetMapper questionSetMapper) {
        this.questionSetRepository = questionSetRepository;
        this.questionSetMapper = questionSetMapper;
    }

    @Override
    public QuestionSetDTO save(QuestionSetDTO questionSetDTO) {
        log.debug("Request to save QuestionSet : {}", questionSetDTO);
        QuestionSet questionSet = questionSetMapper.toEntity(questionSetDTO);
        questionSet = questionSetRepository.save(questionSet);
        return questionSetMapper.toDto(questionSet);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<QuestionSetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all QuestionSets");
        return questionSetRepository.findAll(pageable)
            .map(questionSetMapper::toDto);
    }


    public Page<QuestionSetDTO> findAllWithEagerRelationships(Pageable pageable) {
        return questionSetRepository.findAllWithEagerRelationships(pageable).map(questionSetMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<QuestionSetDTO> findOne(Long id) {
        log.debug("Request to get QuestionSet : {}", id);
        return questionSetRepository.findOneWithEagerRelationships(id)
            .map(questionSetMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete QuestionSet : {}", id);
        questionSetRepository.deleteById(id);
    }
}
