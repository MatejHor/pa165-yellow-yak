package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author matho
 */
public interface CompetitionService {
    /**
     * Find Game by specific id
     * @param gameId if of player
     */
    public List<Competition> findByGame(Long gameId);

    /**
     * Find Competition which have oldest createdAt date
     */
    public LocalDateTime findOldestCompetition();
}
