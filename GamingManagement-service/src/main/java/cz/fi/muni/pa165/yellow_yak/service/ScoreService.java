package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Score;

import java.time.LocalDateTime;
import java.util.List;

public interface ScoreService {
    public List<Score> findByPlayerAndCompetitionAndDate(Long playerId,
                                                         Long competitionId,
                                                         LocalDateTime createdAt);
}
