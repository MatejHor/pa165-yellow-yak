package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Team;

import java.util.List;

/**
 * @author oreqizer
 */
public interface TeamService {

    /**
     * Creates a new team
     * @param name team name
     * @return the created team
     */
    public Team create(String name);

    /**
     * Removes the team
     * @param id id to remove
     */
    public void remove(Long id);

    /**
     * Finds a team by id
     * @param id the ID to find
     * @return the team
     */
    public Team findById(Long id);

    /**
     * Adds a player to the team
     * @param teamId team id
     * @param playerId player id
     */
    public void addPlayer(Long teamId, Long playerId);

    /**
     * Removes a player from the team
     * @param teamId team id
     * @param playerId player id
     */
    public void removePlayer(Long teamId, Long playerId);

    /**
     * Lists teams by name
     * @param name the name to find
     * @return the team list
     */
    public List<Team> listByName(String name);

    /**
     * Finds teams by competition
     * @param competitionId the competition's ID
     * @return the teams found
     */
    public List<Team> listByCompetition(Long competitionId);

    /**
     * Finds teams by player
     * @param playerId the player's ID
     * @return the teams found
     */
    public List<Team> listByPlayer(Long playerId);

}
