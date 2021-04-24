package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author matho
 */
@Service
public class CompetitionServiceImpl implements CompetitionService{
    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public List<Competition> findByGame(Long gameId) {
        return competitionDao.findByGame(gameId);
    }

    @Override
    public LocalDateTime findOldestCompetition() {
        return (competitionDao.findAll().isEmpty()) ? null : competitionDao.findOldest().getCreatedAt();
    }
}
