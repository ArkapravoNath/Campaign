package com.gok.campaign.repository;

import com.gok.campaign.domain.AnswerSet;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the AnswerSet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerSetRepository extends JpaRepository<AnswerSet, Long> {
}
