package com.gok.campaign.service.impl;

import com.gok.campaign.service.AdditionalSymptomsService;
import com.gok.campaign.domain.AdditionalSymptoms;
import com.gok.campaign.repository.AdditionalSymptomsRepository;
import com.gok.campaign.service.dto.AdditionalSymptomsDTO;
import com.gok.campaign.service.mapper.AdditionalSymptomsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link AdditionalSymptoms}.
 */
@Service
@Transactional
public class AdditionalSymptomsServiceImpl implements AdditionalSymptomsService {

    private final Logger log = LoggerFactory.getLogger(AdditionalSymptomsServiceImpl.class);

    private final AdditionalSymptomsRepository additionalSymptomsRepository;

    private final AdditionalSymptomsMapper additionalSymptomsMapper;

    public AdditionalSymptomsServiceImpl(AdditionalSymptomsRepository additionalSymptomsRepository, AdditionalSymptomsMapper additionalSymptomsMapper) {
        this.additionalSymptomsRepository = additionalSymptomsRepository;
        this.additionalSymptomsMapper = additionalSymptomsMapper;
    }

    @Override
    public AdditionalSymptomsDTO save(AdditionalSymptomsDTO additionalSymptomsDTO) {
        log.debug("Request to save AdditionalSymptoms : {}", additionalSymptomsDTO);
        AdditionalSymptoms additionalSymptoms = additionalSymptomsMapper.toEntity(additionalSymptomsDTO);
        additionalSymptoms = additionalSymptomsRepository.save(additionalSymptoms);
        return additionalSymptomsMapper.toDto(additionalSymptoms);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AdditionalSymptomsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AdditionalSymptoms");
        return additionalSymptomsRepository.findAll(pageable)
            .map(additionalSymptomsMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<AdditionalSymptomsDTO> findOne(Long id) {
        log.debug("Request to get AdditionalSymptoms : {}", id);
        return additionalSymptomsRepository.findById(id)
            .map(additionalSymptomsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete AdditionalSymptoms : {}", id);
        additionalSymptomsRepository.deleteById(id);
    }
}
