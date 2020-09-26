package com.gok.campaign.service.impl;

import com.gok.campaign.service.CampTypeService;
import com.gok.campaign.domain.CampType;
import com.gok.campaign.repository.CampTypeRepository;
import com.gok.campaign.service.dto.CampTypeDTO;
import com.gok.campaign.service.mapper.CampTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link CampType}.
 */
@Service
@Transactional
public class CampTypeServiceImpl implements CampTypeService {

    private final Logger log = LoggerFactory.getLogger(CampTypeServiceImpl.class);

    private final CampTypeRepository campTypeRepository;

    private final CampTypeMapper campTypeMapper;

    public CampTypeServiceImpl(CampTypeRepository campTypeRepository, CampTypeMapper campTypeMapper) {
        this.campTypeRepository = campTypeRepository;
        this.campTypeMapper = campTypeMapper;
    }

    @Override
    public CampTypeDTO save(CampTypeDTO campTypeDTO) {
        log.debug("Request to save CampType : {}", campTypeDTO);
        CampType campType = campTypeMapper.toEntity(campTypeDTO);
        campType = campTypeRepository.save(campType);
        return campTypeMapper.toDto(campType);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CampTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CampTypes");
        return campTypeRepository.findAll(pageable)
            .map(campTypeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CampTypeDTO> findOne(Long id) {
        log.debug("Request to get CampType : {}", id);
        return campTypeRepository.findById(id)
            .map(campTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CampType : {}", id);
        campTypeRepository.deleteById(id);
    }
}
