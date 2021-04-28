package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Team;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Matej Knazik
 */
public interface TeamDao {

    /**
     * Create new team
     *
     * @param team team entity
     */
    void create(Team team);

    /**
     * Remove team
     *
     * @param team team entity
     */
    void remove(Team team);

    /**
     * Update existing team
     *
     * @param team team entity
     */
    void update(Team team);

    /**
     * Get all teams from the database
     *
     * @return list of all teams from the database
     */
    List<Team> getAll();

    /**
     * Get team by an id
     *
     * @param id team id of desired team
     * @return list of all teams from the database
     */
    Team getById(Long id);

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
     * Lists teams by a name
     *
     * @param name team's name to filter by
     * @return list of teams with specified name
     */
    List<Team> getByName(String name);

    /**
     * Finds teams by competition
     * @param competitionId the competition's ID
     * @return the teams found
     */
    public List<Team> getByCompetition(Long competitionId);

    /**
     * Finds teams by player
     * @param playerId the player's ID
     * @return the teams found
     */
    public List<Team> getByPlayer(Long playerId);

}