package com.gok.campaign.service.impl;

import com.gok.campaign.service.EssentialsService;
import com.gok.campaign.domain.Essentials;
import com.gok.campaign.repository.EssentialsRepository;
import com.gok.campaign.service.dto.EssentialsDTO;
import com.gok.campaign.service.mapper.EssentialsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Essentials}.
 */
@Service
@Transactional
public class EssentialsServiceImpl implements EssentialsService {

    private final Logger log = LoggerFactory.getLogger(EssentialsServiceImpl.class);

    private final EssentialsRepository essentialsRepository;

    private final EssentialsMapper essentialsMapper;

    public EssentialsServiceImpl(EssentialsRepository essentialsRepository, EssentialsMapper essentialsMapper) {
        this.essentialsRepository = essentialsRepository;
        this.essentialsMapper = essentialsMapper;
    }

    @Override
    public EssentialsDTO save(EssentialsDTO essentialsDTO) {
        log.debug("Request to save Essentials : {}", essentialsDTO);
        Essentials essentials = essentialsMapper.toEntity(essentialsDTO);
        essentials = essentialsRepository.save(essentials);
        return essentialsMapper.toDto(essentials);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EssentialsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Essentials");
        return essentialsRepository.findAll(pageable)
            .map(essentialsMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<EssentialsDTO> findOne(Long id) {
        log.debug("Request to get Essentials : {}", id);
        return essentialsRepository.findById(id)
            .map(essentialsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Essentials : {}", id);
        essentialsRepository.deleteById(id);
    }
}
