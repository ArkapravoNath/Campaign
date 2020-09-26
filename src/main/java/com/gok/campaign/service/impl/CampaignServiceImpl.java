package com.gok.campaign.service.impl;

import com.gok.campaign.service.CampaignService;
import com.gok.campaign.domain.Campaign;
import com.gok.campaign.repository.CampaignRepository;
import com.gok.campaign.service.dto.CampaignDTO;
import com.gok.campaign.service.mapper.CampaignMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Campaign}.
 */
@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

    private final Logger log = LoggerFactory.getLogger(CampaignServiceImpl.class);

    private final CampaignRepository campaignRepository;

    private final CampaignMapper campaignMapper;

    public CampaignServiceImpl(CampaignRepository campaignRepository, CampaignMapper campaignMapper) {
        this.campaignRepository = campaignRepository;
        this.campaignMapper = campaignMapper;
    }

    @Override
    public CampaignDTO save(CampaignDTO campaignDTO) {
        log.debug("Request to save Campaign : {}", campaignDTO);
        Campaign campaign = campaignMapper.toEntity(campaignDTO);
        campaign = campaignRepository.save(campaign);
        return campaignMapper.toDto(campaign);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CampaignDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Campaigns");
        return campaignRepository.findAll(pageable)
            .map(campaignMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<CampaignDTO> findOne(Long id) {
        log.debug("Request to get Campaign : {}", id);
        return campaignRepository.findById(id)
            .map(campaignMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Campaign : {}", id);
        campaignRepository.deleteById(id);
    }
}
