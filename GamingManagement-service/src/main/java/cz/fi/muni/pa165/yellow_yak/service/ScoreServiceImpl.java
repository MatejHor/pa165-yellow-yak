package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.persistance.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService{
    @Autowired
    private ScoreDao scoreDao;

    @Override
    public List<Score> findByPlayerAndCompetitionAndDate(Long playerId,
                                                         Long competitionId,
                                                         LocalDateTime createdAt) {
        return scoreDao.findByPlayerAndCompetitionAndDate(playerId, competitionId, createdAt);
    }
}
