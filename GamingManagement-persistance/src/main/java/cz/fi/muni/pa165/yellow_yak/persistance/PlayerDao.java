package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Player;

import java.util.List;

/**
 * @author Matej Horniak
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
     * @param p player to remove
     */
    void remove(Player p);

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
     * @param teamId team ID
     * @return players
     */
    List<Player> listByTeam(Long teamId);

}
