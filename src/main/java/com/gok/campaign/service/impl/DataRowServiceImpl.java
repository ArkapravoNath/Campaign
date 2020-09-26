package com.gok.campaign.service.impl;

import com.gok.campaign.service.DataRowService;
import com.gok.campaign.domain.DataRow;
import com.gok.campaign.repository.DataRowRepository;
import com.gok.campaign.service.dto.DataRowDTO;
import com.gok.campaign.service.mapper.DataRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DataRow}.
 */
@Service
@Transactional
public class DataRowServiceImpl implements DataRowService {

    private final Logger log = LoggerFactory.getLogger(DataRowServiceImpl.class);

    private final DataRowRepository dataRowRepository;

    private final DataRowMapper dataRowMapper;

    public DataRowServiceImpl(DataRowRepository dataRowRepository, DataRowMapper dataRowMapper) {
        this.dataRowRepository = dataRowRepository;
        this.dataRowMapper = dataRowMapper;
    }

    @Override
    public DataRowDTO save(DataRowDTO dataRowDTO) {
        log.debug("Request to save DataRow : {}", dataRowDTO);
        DataRow dataRow = dataRowMapper.toEntity(dataRowDTO);
        dataRow = dataRowRepository.save(dataRow);
        return dataRowMapper.toDto(dataRow);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DataRowDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DataRows");
        return dataRowRepository.findAll(pageable)
            .map(dataRowMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<DataRowDTO> findOne(Long id) {
        log.debug("Request to get DataRow : {}", id);
        return dataRowRepository.findById(id)
            .map(dataRowMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete DataRow : {}", id);
        dataRowRepository.deleteById(id);
    }
}
