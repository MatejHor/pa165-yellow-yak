package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lukas Mikula
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao playerDao;

    @Override
    public Player create(String name, String email) {
        Player player = new Player();
        player.setUsername(name);
        player.setEmail(email);
        player.setCreatedAt(LocalDateTime.now());

        playerDao.create(player);
        return player;
    }

    @Override
    public void remove(Long id) {
        playerDao.remove(playerDao.findById(id));
    }

    @Override
    public List<Player> findByUsername(String username) {
        return playerDao.findByUsername(username);
    }

    @Override
    public List<Player> findByTeam(Long teamId) {
        return playerDao.findByTeam(teamId);
    }

    @Override
    public Player findById(Long id) {
        if (id == null)
            return null;
        return playerDao.findById(id);
    }

    @Override
    public List<Player> findAll() {
        return playerDao.findAll();
    }

}
