package com.gok.campaign.repository;

import com.gok.campaign.domain.DataColumn;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DataColumn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataColumnRepository extends JpaRepository<DataColumn, Long> {
}
