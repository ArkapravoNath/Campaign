package com.gok.campaign.service.impl;

import com.gok.campaign.service.ZoneService;
import com.gok.campaign.domain.Zone;
import com.gok.campaign.repository.ZoneRepository;
import com.gok.campaign.service.dto.ZoneDTO;
import com.gok.campaign.service.mapper.ZoneMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Zone}.
 */
@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {

    private final Logger log = LoggerFactory.getLogger(ZoneServiceImpl.class);

    private final ZoneRepository zoneRepository;

    private final ZoneMapper zoneMapper;

    public ZoneServiceImpl(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    @Override
    public ZoneDTO save(ZoneDTO zoneDTO) {
        log.debug("Request to save Zone : {}", zoneDTO);
        Zone zone = zoneMapper.toEntity(zoneDTO);
        zone = zoneRepository.save(zone);
        return zoneMapper.toDto(zone);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ZoneDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Zones");
        return zoneRepository.findAll(pageable)
            .map(zoneMapper::toDto);
    }


    public Page<ZoneDTO> findAllWithEagerRelationships(Pageable pageable) {
        return zoneRepository.findAllWithEagerRelationships(pageable).map(zoneMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ZoneDTO> findOne(Long id) {
        log.debug("Request to get Zone : {}", id);
        return zoneRepository.findOneWithEagerRelationships(id)
            .map(zoneMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Zone : {}", id);
        zoneRepository.deleteById(id);
    }
}
