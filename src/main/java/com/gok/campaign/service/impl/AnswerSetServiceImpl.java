package com.gok.campaign.service.impl;

import com.gok.campaign.service.AnswerSetService;
import com.gok.campaign.domain.AnswerSet;
import com.gok.campaign.repository.AnswerSetRepository;
import com.gok.campaign.service.dto.AnswerSetDTO;
import com.gok.campaign.service.mapper.AnswerSetMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AnswerSet}.
 */
@Service
@Transactional
public class AnswerSetServiceImpl implements AnswerSetService {

    private final Logger log = LoggerFactory.getLogger(AnswerSetServiceImpl.class);

    private final AnswerSetRepository answerSetRepository;

    private final AnswerSetMapper answerSetMapper;

    public AnswerSetServiceImpl(AnswerSetRepository answerSetRepository, AnswerSetMapper answerSetMapper) {
        this.answerSetRepository = answerSetRepository;
        this.answerSetMapper = answerSetMapper;
    }

    @Override
    public AnswerSetDTO save(AnswerSetDTO answerSetDTO) {
        log.debug("Request to save AnswerSet : {}", answerSetDTO);
        AnswerSet answerSet = answerSetMapper.toEntity(answerSetDTO);
        answerSet = answerSetRepository.save(answerSet);
        return answerSetMapper.toDto(answerSet);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AnswerSetDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AnswerSets");
        return answerSetRepository.findAll(pageable)
            .map(answerSetMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AnswerSetDTO> findOne(Long id) {
        log.debug("Request to get AnswerSet : {}", id);
        return answerSetRepository.findById(id)
            .map(answerSetMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AnswerSet : {}", id);
        answerSetRepository.deleteById(id);
    }
}
