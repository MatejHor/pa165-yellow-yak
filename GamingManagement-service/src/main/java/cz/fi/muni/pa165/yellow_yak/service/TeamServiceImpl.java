package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author oreqizer
 */
@Service
public class TeamServiceImpl implements TeamService {
    // TODO @mknazik implement

    @Autowired
    private TeamDao teamDao;

    @Override
    public Team create(String name) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public Team findById(Long id) {
        return null;
    }

    @Override
    public void addPlayer(Long teamId, Long playerId) {

    }

    @Override
    public void removePlayer(Long teamId, Long playerId) {

    }

    @Override
    public List<Team> listByName(String name) {
        return null;
    }

    @Override
    public List<Team> listByCompetition(Long competitionId) {
        return null;
    }

    @Override
    public List<Team> listByPlayer(Long playerId) {
        return null;
    }

}
