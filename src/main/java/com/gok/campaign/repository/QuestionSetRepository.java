package com.gok.campaign.repository;

import com.gok.campaign.domain.QuestionSet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the QuestionSet entity.
 */
@Repository
public interface QuestionSetRepository extends JpaRepository<QuestionSet, Long> {

    @Query(value = "select distinct questionSet from QuestionSet questionSet left join fetch questionSet.questions",
        countQuery = "select count(distinct questionSet) from QuestionSet questionSet")
    Page<QuestionSet> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct questionSet from QuestionSet questionSet left join fetch questionSet.questions")
    List<QuestionSet> findAllWithEagerRelationships();

    @Query("select questionSet from QuestionSet questionSet left join fetch questionSet.questions where questionSet.id =:id")
    Optional<QuestionSet> findOneWithEagerRelationships(@Param("id") Long id);
}
