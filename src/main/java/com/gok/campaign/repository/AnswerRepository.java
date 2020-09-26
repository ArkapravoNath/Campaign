package com.gok.campaign.repository;

import com.gok.campaign.domain.Answer;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Answer entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}
