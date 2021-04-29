package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * For 2 milestone
 * @author oreqizer, D1LL1G4F
 */
public interface CompetitionFacade {

    /**
     * Creates a new competition
     * @param gameId ID of the game
     * @param name competition name
     * @return the created competition
     */
    // TODO D1LL1G4F create
    public CompetitionDTO create(Long gameId, String name);

    /**
     * Removes the competition
     * @param id id to remove
     */
    // TODO oreqizer remove
    public void remove(Long id);

    /**
     * Finds a competition by id
     * @param id the ID to find
     * @return the competition
     */
    // TODO D1LL1G4F findById
    public CompetitionDTO findById(Long id);

    /**
     * Returns all competitions for this game
     * @param gameId the game to filter by
     * @param since since when to display competitions
     * @return list of competitions
     */
    // TODO D1LL1G4F findByGame
    public List<CompetitionDTO> findByGame(Long gameId, LocalDateTime since);

}
