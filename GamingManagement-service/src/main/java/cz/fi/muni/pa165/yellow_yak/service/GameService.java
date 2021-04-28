package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

/**
 * @author Lukas Mikula
 */
public interface GameService {

    /**
     * Creates a new game
     * @param name game name
     * @return the created game
     */
    public Game create(String name);

    /**
     * Removes the game
     * @param id id to remove
     */
    public void remove(Long id);

    /**
     * Finds a game by id
     * @param id the ID to find
     * @return the game
     */
    public Game findById(Long id);

    /**
     * Lists games by name
     * @param name the name to find
     * @return the game list
     */
    public List<Game> listByName(String name);

    /**
     * Returns all games
     * @return list of games
     */
    public List<Game> listAll();

}
