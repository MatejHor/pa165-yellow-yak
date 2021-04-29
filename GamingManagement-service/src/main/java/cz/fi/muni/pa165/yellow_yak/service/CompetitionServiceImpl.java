package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author matho, oreqizer
 */
@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

    @Autowired
    private GameDao gameDao;

    @Override
    public Competition create(@NotNull Long gameId, @NotNull String name) {
        Competition competition = new Competition();
        competition.setGame(gameDao.findById(gameId));
        competition.setName(name);
        competition.setCreatedAt(LocalDateTime.now());

        competitionDao.create(competition);
        return competition;
    }

    @Override
    public void remove(@NotNull Long competitionId) {
        competitionDao.remove(competitionDao.findById(competitionId));
    }

    @Override
    public Competition findById(@NotNull Long id) {
        return competitionDao.findById(id);
    }

    @Override
    public List<Competition> findByGame(@NotNull Long gameId) {
        return competitionDao.findByGame(gameId);
    }

    @Override
    public LocalDateTime findOldestCompetition() {
        return (competitionDao.findAll().isEmpty()) ? null : competitionDao.findOldest().getCreatedAt();
    }

}
