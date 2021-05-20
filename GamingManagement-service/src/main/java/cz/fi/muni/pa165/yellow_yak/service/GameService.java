package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import javax.validation.constraints.NotNull;
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
    public Game create(@NotNull String name);

    /**
     * Removes the game
     * @param id game ID to remove
     */
    public boolean remove(@NotNull Long id);

    /**
     * Finds a game by id
     * @param id the id to find
     * @return the competition
     */
    public Game findById(@NotNull Long id);

    /**
     * Finds all games
     * @return list of all games
     */
    public List<Game> findAll();

    /**
     * Returns all games for this name
     * @param name the name to filter by
     * @return list of games
     */
    public List<Game> findByName(@NotNull String name);
}
