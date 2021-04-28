package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;

import java.time.LocalDateTime;
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
     * @param c competition to remove
     */
    public void remove(CompetitionDTO c);

    /**
     * Finds a competition by name
     * @param name the name to find
     * @return the competition
     */
    public CompetitionDTO findByName(String name);

    /**
     * Returns all competitions for this game
     * @param gameId the game to filter by
     * @param since since when to display competitions
     * @return list of competitions
     */
    public List<CompetitionDTO> listByGame(Long gameId, LocalDateTime since);

}
