package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author oreqizer
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    private TeamDao teamDao;

    @Override
    public Team create(String name) {
        // TODO
        return null;
    }

    @Override
    public void remove(Long id) {
        // TODO
    }

    @Override
    public Team findById(Long id) {
        // TODO
        return null;
    }

    @Override
    public void addPlayer(Long teamId, Long playerId) {
        // TODO
    }

    @Override
    public void removePlayer(Long teamId, Long playerId) {
        // TODO
    }

    @Override
    public List<Team> listByName(String name) {
        // TODO
        return null;
    }

    @Override
    public List<Team> listByCompetition(Long competitionId) {
        // TODO
        return null;
    }

    @Override
    public List<Team> listByPlayer(Long playerId) {
        // TODO
        return null;
    }

}
