package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Lukas Mikula, oreqizer
 */
@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    @Autowired
    private TeamDao teamDao;

    @Override
    public Player create(String name, String email, Long teamId) {
        Player player = new Player();
        player.setUsername(name);
        player.setEmail(email);
        player.setCreatedAt(LocalDate.now());
        player.setTeam(teamDao.findById(teamId));

        playerDao.create(player);
        return player;
    }

    @Override
    public boolean remove(@NotNull Long id) {
        playerDao.remove(id);
        return playerDao.findById(id) == null;
    }

    @Override
    public List<Player> findByUsername(@NotNull String username) {
        return playerDao.findByUsername(username);
    }

    @Override
    public List<Player> findByTeam(@NotNull Long teamId) {
        return playerDao.findByTeam(teamDao.findById(teamId));
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
