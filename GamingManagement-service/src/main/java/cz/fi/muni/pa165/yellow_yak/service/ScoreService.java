package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Score;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author matho
 */
public interface ScoreService {
    /**
     * Find Score by specific Player, specific Competition and specific Date
     * @param playerId if of player
     * @param competitionId if of competition
     * @param createdAt specific date
     */
    public List<Score> findByPlayerAndCompetitionAndDate(Long playerId,
                                                         Long competitionId,
                                                         LocalDateTime createdAt);
}
