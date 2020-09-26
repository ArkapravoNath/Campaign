package com.gok.campaign.repository;

import com.gok.campaign.domain.FileParseRequest;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FileParseRequest entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FileParseRequestRepository extends JpaRepository<FileParseRequest, Long> {
}
