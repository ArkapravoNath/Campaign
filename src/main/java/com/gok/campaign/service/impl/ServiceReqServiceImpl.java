package com.gok.campaign.service.impl;

import com.gok.campaign.service.ServiceReqService;
import com.gok.campaign.domain.ServiceReq;
import com.gok.campaign.repository.ServiceReqRepository;
import com.gok.campaign.service.dto.ServiceReqDTO;
import com.gok.campaign.service.mapper.ServiceReqMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing {@link ServiceReq}.
 */
@Service
@Transactional
public class ServiceReqServiceImpl implements ServiceReqService {

    private final Logger log = LoggerFactory.getLogger(ServiceReqServiceImpl.class);

    private final ServiceReqRepository serviceReqRepository;

    private final ServiceReqMapper serviceReqMapper;

    public ServiceReqServiceImpl(ServiceReqRepository serviceReqRepository, ServiceReqMapper serviceReqMapper) {
        this.serviceReqRepository = serviceReqRepository;
        this.serviceReqMapper = serviceReqMapper;
    }

    @Override
    public ServiceReqDTO save(ServiceReqDTO serviceReqDTO) {
        log.debug("Request to save ServiceReq : {}", serviceReqDTO);
        ServiceReq serviceReq = serviceReqMapper.toEntity(serviceReqDTO);
        serviceReq = serviceReqRepository.save(serviceReq);
        return serviceReqMapper.toDto(serviceReq);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ServiceReqDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceReqs");
        return serviceReqRepository.findAll(pageable)
            .map(serviceReqMapper::toDto);
    }



    /**
     *  Get all the serviceReqs where Essentials is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true) 
    public List<ServiceReqDTO> findAllWhereEssentialsIsNull() {
        log.debug("Request to get all serviceReqs where Essentials is null");
        return StreamSupport
            .stream(serviceReqRepository.findAll().spliterator(), false)
            .filter(serviceReq -> serviceReq.getEssentials() == null)
            .map(serviceReqMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceReqDTO> findOne(Long id) {
        log.debug("Request to get ServiceReq : {}", id);
        return serviceReqRepository.findById(id)
            .map(serviceReqMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceReq : {}", id);
        serviceReqRepository.deleteById(id);
    }
}
