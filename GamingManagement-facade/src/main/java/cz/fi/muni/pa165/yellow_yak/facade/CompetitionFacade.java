package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;

import java.util.List;

/**
 * @author oreqizer
 */
public interface CompetitionFacade {

    /**
     * Creates a new competition
     * @param gameId ID of the game
     * @param name competition name
     * @return the created competition
     */
    public CompetitionDTO create(Long gameId, String name);

    /**
     * Removes the competition
     * @param id id to remove
     */
    public void remove(Long id);

    /**
     * Finds a competition by id
     * @param id the ID to find
     * @return the competition
     */
    public CompetitionDTO findById(Long id);

    /**
     * Returns all competitions for this game
     * @param gameId the game to filter by
     * @return list of competitions
     */
    public List<CompetitionDTO> findByGame(Long gameId);

}
