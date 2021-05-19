package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author matho, oreqizer
 */
public interface CompetitionService {

    /**
     * Creates a new competition
     * @param gameId ID of the game
     * @param name competition name
     * @return the created competition
     */
    public Competition create(@NotNull Long gameId, @NotNull String name);

    /**
     * Removes the competition
     * @param competitionId competition ID to remove
     */
    public boolean remove(@NotNull Long competitionId);

    /**
     * Finds a competition by id
     * @param id the id to find
     * @return the competition
     */
    public Competition findById(@NotNull Long id);

    /**
     * Returns all competitions for this game
     * @param gameId the game to filter by
     * @return list of competitions
     */
    public List<Competition> findByGame(@NotNull Long gameId);

    /**
     * Find Competition which have oldest createdAt date
     */
    public LocalDate findOldestCompetition();
}
