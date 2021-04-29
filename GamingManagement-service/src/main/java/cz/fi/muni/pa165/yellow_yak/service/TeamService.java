package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Team;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Matej Knazik
 */
public interface TeamService {

    /**
     * Creates a new team
     * @param name team name
     * @return newly created team
     */
    public Team create(@NotNull String name);

    /**
     * Removes the team
     * @param teamId team ID to remove
     */
    public void remove(@NotNull Long teamId);

    /**
     * Finds a team by an team id
     * @param teamId the id to find
     * @return the team or null in case there is no such a team
     */
    public Team findById(@NotNull Long teamId);

    /**
     * Returns all teams with specified name
     * @param name the team name to filter by
     * @return list of found names or null in case there is no such a team
     */
    public List<Team> findByName(@NotNull String name);
}
