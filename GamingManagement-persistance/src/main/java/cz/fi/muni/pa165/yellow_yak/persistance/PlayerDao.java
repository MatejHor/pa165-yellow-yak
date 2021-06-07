package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Team;

import java.util.List;

/**
 * Player DAO interface
 *
 * @author Matej Horniak, oreqizer
 */
public interface PlayerDao {


    /**
     * Create new player
     * @param p player to create
     */
    void create(Player p);

    /**
     * Return all players in the db
     * @return List of player
     */
    List<Player> findAll();

    /**
     * Return player with specific id
     * @param id specific id
     * @return player
     */
    Player findById(Long id);

    /**
     * Remove player
     * @param id player to remove
     */
    void remove(Long id);

    /**
     * Update player
     * @param p player to player
     */
    void update(Player p);

    /**
     * Return all player with specific username in db
     * @param username specific player username
     * @return List of players
     */
    List<Player> findByUsername(String username);

    /**
     * List players by team
     * @param team team
     * @return players
     */
    List<Player> findByTeam(Team team);

}
