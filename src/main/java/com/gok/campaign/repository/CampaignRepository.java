package com.gok.campaign.repository;

import com.gok.campaign.domain.Campaign;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Campaign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
