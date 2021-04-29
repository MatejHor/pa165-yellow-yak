package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Team;

import javax.validation.constraints.NotNull;
import java.util.List;

public class TeamServiceImpl implements TeamService{
    @Override
    public Team create(@NotNull String name) {
        return null;
    }

    @Override
    public void remove(@NotNull Long teamId) {

    }

    @Override
    public Team findById(@NotNull Long teamId) {
        return null;
    }

    @Override
    public List<Team> findByName(@NotNull String name) {
        return null;
    }
}
