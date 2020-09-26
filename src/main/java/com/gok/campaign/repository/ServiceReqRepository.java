package com.gok.campaign.repository;

import com.gok.campaign.domain.ServiceReq;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ServiceReq entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceReqRepository extends JpaRepository<ServiceReq, Long> {
}
