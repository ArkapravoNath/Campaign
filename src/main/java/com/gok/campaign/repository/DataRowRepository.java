package com.gok.campaign.repository;

import com.gok.campaign.domain.DataRow;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DataRow entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DataRowRepository extends JpaRepository<DataRow, Long> {
}
