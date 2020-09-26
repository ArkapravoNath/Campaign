package com.gok.campaign.service.impl;

import com.gok.campaign.service.FileParseRequestService;
import com.gok.campaign.domain.FileParseRequest;
import com.gok.campaign.repository.FileParseRequestRepository;
import com.gok.campaign.service.dto.FileParseRequestDTO;
import com.gok.campaign.service.mapper.FileParseRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link FileParseRequest}.
 */
@Service
@Transactional
public class FileParseRequestServiceImpl implements FileParseRequestService {

    private final Logger log = LoggerFactory.getLogger(FileParseRequestServiceImpl.class);

    private final FileParseRequestRepository fileParseRequestRepository;

    private final FileParseRequestMapper fileParseRequestMapper;

    public FileParseRequestServiceImpl(FileParseRequestRepository fileParseRequestRepository, FileParseRequestMapper fileParseRequestMapper) {
        this.fileParseRequestRepository = fileParseRequestRepository;
        this.fileParseRequestMapper = fileParseRequestMapper;
    }

    @Override
    public FileParseRequestDTO save(FileParseRequestDTO fileParseRequestDTO) {
        log.debug("Request to save FileParseRequest : {}", fileParseRequestDTO);
        FileParseRequest fileParseRequest = fileParseRequestMapper.toEntity(fileParseRequestDTO);
        fileParseRequest = fileParseRequestRepository.save(fileParseRequest);
        return fileParseRequestMapper.toDto(fileParseRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<FileParseRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FileParseRequests");
        return fileParseRequestRepository.findAll(pageable)
            .map(fileParseRequestMapper::toDto);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<FileParseRequestDTO> findOne(Long id) {
        log.debug("Request to get FileParseRequest : {}", id);
        return fileParseRequestRepository.findById(id)
            .map(fileParseRequestMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete FileParseRequest : {}", id);
        fileParseRequestRepository.deleteById(id);
    }
}
