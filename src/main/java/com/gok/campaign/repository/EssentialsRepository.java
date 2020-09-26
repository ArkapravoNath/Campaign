package com.gok.campaign.repository;

import com.gok.campaign.domain.Essentials;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Essentials entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EssentialsRepository extends JpaRepository<Essentials, Long> {
}
