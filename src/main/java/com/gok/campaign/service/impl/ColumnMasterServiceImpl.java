package com.gok.campaign.service.impl;

import com.gok.campaign.service.ColumnMasterService;
import com.gok.campaign.domain.ColumnMaster;
import com.gok.campaign.repository.ColumnMasterRepository;
import com.gok.campaign.service.dto.ColumnMasterDTO;
import com.gok.campaign.service.mapper.ColumnMasterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link ColumnMaster}.
 */
@Service
@Transactional
public class ColumnMasterServiceImpl implements ColumnMasterService {

    private final Logger log = LoggerFactory.getLogger(ColumnMasterServiceImpl.class);

    private final ColumnMasterRepository columnMasterRepository;

    private final ColumnMasterMapper columnMasterMapper;

    public ColumnMasterServiceImpl(ColumnMasterRepository columnMasterRepository, ColumnMasterMapper columnMasterMapper) {
        this.columnMasterRepository = columnMasterRepository;
        this.columnMasterMapper = columnMasterMapper;
    }

    @Override
    public ColumnMasterDTO save(ColumnMasterDTO columnMasterDTO) {
        log.debug("Request to save ColumnMaster : {}", columnMasterDTO);
        ColumnMaster columnMaster = columnMasterMapper.toEntity(columnMasterDTO);
        columnMaster = columnMasterRepository.save(columnMaster);
        return columnMasterMapper.toDto(columnMaster);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ColumnMasterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ColumnMasters");
        return columnMasterRepository.findAll(pageable)
            .map(columnMasterMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<ColumnMasterDTO> findOne(Long id) {
        log.debug("Request to get ColumnMaster : {}", id);
        return columnMasterRepository.findById(id)
            .map(columnMasterMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ColumnMaster : {}", id);
        columnMasterRepository.deleteById(id);
    }
}
