package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;

/**
 * @author matho
 */
@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Autowired
    private CompetitionDao competitionDao;

    @Autowired
    private GameDao gameDao;

    @Override
    public Competition create(Long gameId, String name) {
        Competition competition = new Competition();
        competition.setGame(gameDao.findById(gameId));
        competition.setName(name);
        competition.setCreatedAt(LocalDate.now());
        competition.setStartedAt(LocalDate.now());

        competitionDao.create(competition);
        return competition;
    }

    @Override
    public List<Competition> findAll() {
        return competitionDao.findAll();
    }

    @Override
    public boolean remove(Long competitionId) {
        competitionDao.remove(competitionDao.findById(competitionId));
        return competitionDao.findById(competitionId) == null;
    }

    @Override
    public Competition findById(Long id) {
        if (id == null)
            return null;
        return competitionDao.findById(id);
    }

    @Override
    public List<Competition> findByGame(Long gameId) {
        return competitionDao.findByGame(gameDao.findById(gameId));
    }

    @Override
    public LocalDate findOldestCompetition() {
        return (competitionDao.findAll().isEmpty()) ? null : competitionDao.findOldest().getCreatedAt();
    }
}
