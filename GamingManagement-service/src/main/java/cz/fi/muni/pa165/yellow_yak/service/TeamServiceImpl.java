package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * @author Matej Knazik
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Override
    public Team create(@NotNull String name) {
        if (name != null) {
            Team newTeam = new Team();
            newTeam.setName(name);
            newTeam.setCreatedAt(LocalDateTime.now());

            teamDao.create(newTeam);
            return newTeam;
        }

        return null;
    }

    @Override
    public void remove(@NotNull Long teamId) {
        if (teamId != null)
            teamDao.remove(teamDao.findById(teamId));
        return;
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
        return teamDao.getAll();
    }
}
