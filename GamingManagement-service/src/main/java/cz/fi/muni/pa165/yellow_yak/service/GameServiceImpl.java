package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Lukas Mikula
 */
@Service
public class GameServiceImpl implements GameService{
    @Autowired
    private GameDao gameDao;

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
    public void remove(Long id) {
        if (id == null) return;

        gameDao.remove(gameDao.findById(id));
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
