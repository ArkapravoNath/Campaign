package com.gok.campaign.repository;

import com.gok.campaign.domain.ZoneType;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ZoneType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ZoneTypeRepository extends JpaRepository<ZoneType, Long> {
}
