package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

/**
 * @author Matej Horniak
 */
public interface GameDao {

    /**
     * Create new game
     * @param g game to create
     */
    void create(Game g);

    /**
     * Return all game in db
     * @return List of games
     */
    List<Game> findAll();

    /**
     * Return game with specific id
     * @param id specific id
     * @return Game
     */
    Game findById(Long id);

    /**
     * Remove game
     * @param g game to remove
     */
    void remove(Game g);

    /**
     * Update game
     * @param g game to update
     */
    void update(Game g);

    /**
     * Return all game with specific name in db
     * @param name specific game name
     * @return List of games
     */
    Game findByName(String name) ;
}
