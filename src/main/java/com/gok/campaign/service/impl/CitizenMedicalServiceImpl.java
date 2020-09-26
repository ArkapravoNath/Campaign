package com.gok.campaign.service.impl;

import com.gok.campaign.service.CitizenMedicalService;
import com.gok.campaign.domain.CitizenMedical;
import com.gok.campaign.repository.CitizenMedicalRepository;
import com.gok.campaign.service.dto.CitizenMedicalDTO;
import com.gok.campaign.service.mapper.CitizenMedicalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CitizenMedical}.
 */
@Service
@Transactional
public class CitizenMedicalServiceImpl implements CitizenMedicalService {

    private final Logger log = LoggerFactory.getLogger(CitizenMedicalServiceImpl.class);

    private final CitizenMedicalRepository citizenMedicalRepository;

    private final CitizenMedicalMapper citizenMedicalMapper;

    public CitizenMedicalServiceImpl(CitizenMedicalRepository citizenMedicalRepository, CitizenMedicalMapper citizenMedicalMapper) {
        this.citizenMedicalRepository = citizenMedicalRepository;
        this.citizenMedicalMapper = citizenMedicalMapper;
    }

    @Override
    public CitizenMedicalDTO save(CitizenMedicalDTO citizenMedicalDTO) {
        log.debug("Request to save CitizenMedical : {}", citizenMedicalDTO);
        CitizenMedical citizenMedical = citizenMedicalMapper.toEntity(citizenMedicalDTO);
        citizenMedical = citizenMedicalRepository.save(citizenMedical);
        return citizenMedicalMapper.toDto(citizenMedical);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CitizenMedicalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CitizenMedicals");
        return citizenMedicalRepository.findAll(pageable)
            .map(citizenMedicalMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CitizenMedicalDTO> findOne(Long id) {
        log.debug("Request to get CitizenMedical : {}", id);
        return citizenMedicalRepository.findById(id)
            .map(citizenMedicalMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CitizenMedical : {}", id);
        citizenMedicalRepository.deleteById(id);
    }
}
