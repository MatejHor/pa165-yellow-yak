package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * Facade for Score
 *
 * @author Matej Horniak, oreqizer
 */
public interface ScoreFacade {
    /**
     * Lists score statistics
     * @param playerId player id to search
     * @param gameId   game id to search
     * @param oldest   date to search
     * @return list of scores
     */
    public List<ScoreDTO> findByGamePlayerDate(@NotNull Long playerId, @NotNull Long gameId, LocalDate oldest);

    /**
     * Creates a new score
     *
     * @param competitionId competition ID
     * @param playerId      player ID
     * @return the created score
     */
    public ScoreDTO create(@NotNull Long competitionId, @NotNull Long playerId);

    /**
     * Removes the score
     *
     * @param id id to remove
     * @return
     */
    public boolean remove(@NotNull Long id);

    /**
     * Finds a score by id
     *
     * @param id the ID to find
     * @return the score
     */
    public ScoreDTO findById(@NotNull Long id);

    /**
     * Sets the score's result and recalculates rank
     * @param id score ID
     * @param result result
     * @return updated score
     */
    public ScoreDTO setResult(@NotNull Long id, int result);

    /**
     * Lists score statistics for the player
     *
     * @param playerId player id to search by
     * @param gameId   game id to search by
     * @return list of scores
     */
    public List<ScoreDTO> findByPlayerGame(@NotNull Long playerId, @NotNull Long gameId);

    /**
     * Lists score statistics for the competition
     *
     * @param competitionId id to search by
     * @return list of scores
     */
    public List<ScoreDTO> findByCompetition(@NotNull Long competitionId);

}
