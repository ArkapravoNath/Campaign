package com.gok.campaign.service.impl;

import com.gok.campaign.service.MedicalSymptomService;
import com.gok.campaign.domain.MedicalSymptom;
import com.gok.campaign.repository.MedicalSymptomRepository;
import com.gok.campaign.service.dto.MedicalSymptomDTO;
import com.gok.campaign.service.mapper.MedicalSymptomMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MedicalSymptom}.
 */
@Service
@Transactional
public class MedicalSymptomServiceImpl implements MedicalSymptomService {

    private final Logger log = LoggerFactory.getLogger(MedicalSymptomServiceImpl.class);

    private final MedicalSymptomRepository medicalSymptomRepository;

    private final MedicalSymptomMapper medicalSymptomMapper;

    public MedicalSymptomServiceImpl(MedicalSymptomRepository medicalSymptomRepository, MedicalSymptomMapper medicalSymptomMapper) {
        this.medicalSymptomRepository = medicalSymptomRepository;
        this.medicalSymptomMapper = medicalSymptomMapper;
    }

    @Override
    public MedicalSymptomDTO save(MedicalSymptomDTO medicalSymptomDTO) {
        log.debug("Request to save MedicalSymptom : {}", medicalSymptomDTO);
        MedicalSymptom medicalSymptom = medicalSymptomMapper.toEntity(medicalSymptomDTO);
        medicalSymptom = medicalSymptomRepository.save(medicalSymptom);
        return medicalSymptomMapper.toDto(medicalSymptom);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MedicalSymptomDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MedicalSymptoms");
        return medicalSymptomRepository.findAll(pageable)
            .map(medicalSymptomMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<MedicalSymptomDTO> findOne(Long id) {
        log.debug("Request to get MedicalSymptom : {}", id);
        return medicalSymptomRepository.findById(id)
            .map(medicalSymptomMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MedicalSymptom : {}", id);
        medicalSymptomRepository.deleteById(id);
    }
}
