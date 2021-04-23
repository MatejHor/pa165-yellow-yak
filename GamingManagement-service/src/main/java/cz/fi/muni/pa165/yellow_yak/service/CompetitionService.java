package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;

import java.time.LocalDateTime;
import java.util.List;

public interface CompetitionService {
    public List<Competition> findByGame(Long gameId);
    public LocalDateTime findOldestCompetition();
}
