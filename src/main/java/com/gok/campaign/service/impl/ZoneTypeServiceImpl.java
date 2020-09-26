package com.gok.campaign.service.impl;

import com.gok.campaign.service.ZoneTypeService;
import com.gok.campaign.domain.ZoneType;
import com.gok.campaign.repository.ZoneTypeRepository;
import com.gok.campaign.service.dto.ZoneTypeDTO;
import com.gok.campaign.service.mapper.ZoneTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ZoneType}.
 */
@Service
@Transactional
public class ZoneTypeServiceImpl implements ZoneTypeService {

    private final Logger log = LoggerFactory.getLogger(ZoneTypeServiceImpl.class);

    private final ZoneTypeRepository zoneTypeRepository;

    private final ZoneTypeMapper zoneTypeMapper;

    public ZoneTypeServiceImpl(ZoneTypeRepository zoneTypeRepository, ZoneTypeMapper zoneTypeMapper) {
        this.zoneTypeRepository = zoneTypeRepository;
        this.zoneTypeMapper = zoneTypeMapper;
    }

    @Override
    public ZoneTypeDTO save(ZoneTypeDTO zoneTypeDTO) {
        log.debug("Request to save ZoneType : {}", zoneTypeDTO);
        ZoneType zoneType = zoneTypeMapper.toEntity(zoneTypeDTO);
        zoneType = zoneTypeRepository.save(zoneType);
        return zoneTypeMapper.toDto(zoneType);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ZoneTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ZoneTypes");
        return zoneTypeRepository.findAll(pageable)
            .map(zoneTypeMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ZoneTypeDTO> findOne(Long id) {
        log.debug("Request to get ZoneType : {}", id);
        return zoneTypeRepository.findById(id)
            .map(zoneTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ZoneType : {}", id);
        zoneTypeRepository.deleteById(id);
    }
}
