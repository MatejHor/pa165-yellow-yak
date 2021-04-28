package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Lukas Mikula
 */
@Service
public class GameServiceImpl implements GameService{
    @Inject
    private GameDao gameDao;

    @Override
    public Game create(String name) {
        // TODO
        return null;
    }

    @Override
    public void remove(Long id) {
        // TODO
    }

    @Override
    public Game findById(Long gameId) {
        return gameDao.findById(gameId);
    }

    @Override
    public List<Game> listByName(String name) {
        // TODO
        return null;
    }

    @Override
    public List<Game> listAll() {
        return gameDao.findAll();
    }

}
