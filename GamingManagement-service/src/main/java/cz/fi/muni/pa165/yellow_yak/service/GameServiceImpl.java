package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * Implementation for game service layer
 *
 * @author Lukas Mikula
 */
@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private GameDao gameDao;

    @Autowired
    private CompetitionDao competitionDao;

    @Override
    public Game create(String name) {
        if (name == null) return null;

        Game game = new Game();
        game.setName(name);
        game.setCreatedAt(LocalDate.now());

        gameDao.create(game);
        return game;
    }

    @Override
    public boolean remove(Long id) {
        competitionDao.findByGame(gameDao.findById(id)).stream().forEach(competition -> competitionDao.remove(competition));
        gameDao.remove(gameDao.findById(id));
        return gameDao.findById(id) == null;
    }

    @Override
    public Game findById(Long id) {
        return gameDao.findById(id);
    }

    @Override
    public List<Game> findAll() {
        return gameDao.findAll();
    }

    @Override
    public List<Game> findByName(String name) {
        if (name == null) return null;

        return gameDao.findByName(name);
    }
}
