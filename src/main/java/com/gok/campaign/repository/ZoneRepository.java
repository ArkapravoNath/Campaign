package com.gok.campaign.repository;

import com.gok.campaign.domain.Zone;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Zone entity.
 */
@Repository
public interface ZoneRepository extends JpaRepository<Zone, Long> {

    @Query(value = "select distinct zone from Zone zone left join fetch zone.zoneTypes left join fetch zone.locations",
        countQuery = "select count(distinct zone) from Zone zone")
    Page<Zone> findAllWithEagerRelationships(Pageable pageable);

    @Query("select distinct zone from Zone zone left join fetch zone.zoneTypes left join fetch zone.locations")
    List<Zone> findAllWithEagerRelationships();

    @Query("select zone from Zone zone left join fetch zone.zoneTypes left join fetch zone.locations where zone.id =:id")
    Optional<Zone> findOneWithEagerRelationships(@Param("id") Long id);
}
