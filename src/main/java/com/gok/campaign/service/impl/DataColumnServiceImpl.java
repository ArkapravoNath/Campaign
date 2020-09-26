package com.gok.campaign.service.impl;

import com.gok.campaign.service.DataColumnService;
import com.gok.campaign.domain.DataColumn;
import com.gok.campaign.repository.DataColumnRepository;
import com.gok.campaign.service.dto.DataColumnDTO;
import com.gok.campaign.service.mapper.DataColumnMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DataColumn}.
 */
@Service
@Transactional
public class DataColumnServiceImpl implements DataColumnService {

    private final Logger log = LoggerFactory.getLogger(DataColumnServiceImpl.class);

    private final DataColumnRepository dataColumnRepository;

    private final DataColumnMapper dataColumnMapper;

    public DataColumnServiceImpl(DataColumnRepository dataColumnRepository, DataColumnMapper dataColumnMapper) {
        this.dataColumnRepository = dataColumnRepository;
        this.dataColumnMapper = dataColumnMapper;
    }

    @Override
    public DataColumnDTO save(DataColumnDTO dataColumnDTO) {
        log.debug("Request to save DataColumn : {}", dataColumnDTO);
        DataColumn dataColumn = dataColumnMapper.toEntity(dataColumnDTO);
        dataColumn = dataColumnRepository.save(dataColumn);
        return dataColumnMapper.toDto(dataColumn);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DataColumnDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataColumns");
        return dataColumnRepository.findAll(pageable)
            .map(dataColumnMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DataColumnDTO> findOne(Long id) {
        log.debug("Request to get DataColumn : {}", id);
        return dataColumnRepository.findById(id)
            .map(dataColumnMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataColumn : {}", id);
        dataColumnRepository.deleteById(id);
    }
}
