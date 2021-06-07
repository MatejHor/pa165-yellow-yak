package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

/**
 * Implementation for team service layer
 *
 * @author Matej Knazik
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Autowired
    private PlayerDao playerDao;

    @Override
    public Team create(@NotNull String name) {
        if (name != null) {
            Team newTeam = new Team();
            newTeam.setName(name);
            newTeam.setCreatedAt(LocalDate.now());

            teamDao.create(newTeam);
            return newTeam;
        }

        return null;
    }

    @Override
    public boolean remove(@NotNull Long teamId) {
        if (teamId == null)
            return false;
        playerDao.findByTeam(teamDao.findById(teamId)).stream().forEach(player -> playerDao.remove(player.getId()));
        teamDao.remove(teamId);
        return teamDao.findById(teamId) == null;
    }

    @Override
    public Team findById(@NotNull Long teamId) {
        if (teamId != null)
            return teamDao.findById(teamId);
        return null;
    }

    @Override
    public List<Team> findByName(@NotNull String name) {
        if (name != null)
            return teamDao.findByName(name);
        return Collections.emptyList();
    }

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }
}
