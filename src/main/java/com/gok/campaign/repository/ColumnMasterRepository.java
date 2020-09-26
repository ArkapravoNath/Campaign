package com.gok.campaign.repository;

import com.gok.campaign.domain.ColumnMaster;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ColumnMaster entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ColumnMasterRepository extends JpaRepository<ColumnMaster, Long> {
}
