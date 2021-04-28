package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;

import java.util.List;

/**
 * @author oreqizer
 */
public interface GameFacade {

    /**
     * Creates a new game
     * @param name game name
     * @return the created game
     */
    public CompetitionDTO create(String name);

    /**
     * Removes the game
     * @param g game to remove
     */
    public void remove(GameDTO g);

    /**
     * Finds a game by name
     * @param name the name to find
     * @return the game
     */
    public GameDTO findByName(String name);

    /**
     * Returns all games for this game
     * @return list of games
     */
    public List<GameDTO> findAll();

}
