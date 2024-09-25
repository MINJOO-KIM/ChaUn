package com.ssafy.health.domain.battle.repository;

import com.ssafy.health.domain.battle.dto.response.BattleStatsDto;
import com.ssafy.health.domain.battle.entity.Battle;
import com.ssafy.health.domain.battle.entity.BattleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BattleRepository extends JpaRepository<Battle, Long> {

    @Query("SELECT b FROM Battle b " +
            "WHERE (b.homeCrew.id IN :crewIdList OR b.awayCrew.id IN :crewIdList) " +
            "AND b.status = 'FINISHED' " +
            "ORDER BY b.createdAt DESC")
    List<Battle> findMostRecentFinishedBattleByCrewIdList(List<Long> crewIdList);

    @Query("SELECT b FROM Battle b " +
            "WHERE (b.homeCrew.id = :crewId OR b.awayCrew.id = :crewId) " +
            "AND b.status = 'FINISHED' " +
            "ORDER BY b.createdAt DESC")
    Optional<Battle> findFirstByCrewIdOrderByCreatedAtDesc(Long crewId);

    @Query("SELECT new com.ssafy.health.domain.battle.dto.response.BattleStatsDto(" +
            "COUNT(b), " +
            "SUM(CASE " +
            "  WHEN (b.homeCrew.id = :crewId AND b.homeCrewScore > b.awayCrewScore) " +
            "    OR (b.awayCrew.id = :crewId AND b.awayCrewScore > b.homeCrewScore) THEN 1 ELSE 0 END)) " +
            "FROM Battle b " +
            "WHERE (b.homeCrew.id = :crewId OR b.awayCrew.id = :crewId) " +
            "AND b.status = 'FINISHED'")
    Optional<BattleStatsDto> countTotalAndWonBattles(Long crewId);

    @Query("SELECT b FROM Battle b WHERE (b.homeCrew.id = :crewId OR b.awayCrew.id = :crewId) AND b.status = :status")
    Optional<Battle> findBattleByCrewId(Long crewId, BattleStatus status);
}
