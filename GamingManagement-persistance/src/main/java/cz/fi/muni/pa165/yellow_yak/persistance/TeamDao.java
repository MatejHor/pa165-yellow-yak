package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
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
     * @param team team
     * @param player player
     */
    public void addPlayer(Team team, Player player);

    /**
     * Removes a player from the team
     * @param team team
     * @param player player
     */
    public void removePlayer(Team team, Player player);

    /**
     * Lists teams by a name
     *
     * @param name team's name to filter by
     * @return list of teams with specified name
     */
    List<Team> getByName(String name);

    /**
     * Finds teams by competition
     * @param competition the competition
     * @return the teams found
     */
    public List<Team> getByCompetition(Competition competition);

    /**
     * Finds teams by player
     * @param player the player
     * @return the teams found
     */
    public List<Team> getByPlayer(Player player);

}