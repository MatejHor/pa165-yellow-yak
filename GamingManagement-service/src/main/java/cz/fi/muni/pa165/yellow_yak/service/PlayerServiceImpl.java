package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lukas Mikula
 */
@Service
public class PlayerServiceImpl implements PlayerService{
    @Autowired
    private PlayerDao playerDao;

    @Override
    public List<Player> findAll() {
        return playerDao.findAll();
    }

    @Override
    public Player find(Long playerId) {
        return playerDao.findById(playerId);
    }
}
