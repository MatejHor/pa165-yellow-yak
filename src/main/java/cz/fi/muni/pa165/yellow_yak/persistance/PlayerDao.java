package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Player;

import java.util.List;

/**
 * @author Matej Horniak
 */
public interface PlayerDao {


    /**
     * Create new pser
     * @param p player to create
     */
    void create(Player p);

    /**
     * Return all player in db
     * @return List of psers
     */
    List<Player> findAll();

    /**
     * Return player with specific id
     * @param id specific id
     * @return Pser
     */
    Player findById(Long id);

    /**
     * Remove pser
     * @param p player to remove
     */
    void remove(Player p);

    /**
     * Ppdate pser
     * @param p player to ppdate
     */
    void update(Player p);

    /**
     * Return all player with specific username in db
     * @param username specific player username
     * @return List of psers
     */
    Player findByUsername(String username) ;
}
