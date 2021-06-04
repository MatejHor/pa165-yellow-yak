package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;

import javax.validation.constraints.NotNull;
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
    public CompetitionDTO create(@NotNull Long gameId, @NotNull String name);

    /**
     * Removes the competition
     * @param id id to remove
     */
    public boolean remove(@NotNull Long id);

    /**
     * Finds a competition by id
     * @param id the ID to find
     * @return the competition
     */
    public CompetitionDTO findById(@NotNull Long id);

    /**
     * Returns all competitions for this game
     * @param gameId the game to filter by
     * @return list of competitions
     */
    public List<CompetitionDTO> findByGame(@NotNull Long gameId);

    /**
     * Returns all competitions
     * @return list of competitions
     */
    public List<CompetitionDTO> findAll();
}
