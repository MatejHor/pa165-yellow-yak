package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * For 2 milestone
 * @author Matej Horniak, oreqizer
 */
public interface ScoreFacade {
    /**
     * Lists score statistics
     * @param playerId player id to search
     * @param gameId game id to search
     * @param oldest date to search
     * @return list of scores
     */
    public List<ScoreDTO> findByGamePlayerDate(Long playerId, Long gameId, LocalDateTime oldest);

    // TODO oreqizer createScore
    public String createScore(Long competitionId, Long playerId);

    /**
     * Creates a new score
     * @param competitionId competition ID
     * @param playerId player ID
     * @return the created score
     */
    public ScoreDTO create(Long competitionId, Long playerId);

    /**
     * Removes the score
     * @param id id to remove
     */
    public void remove(Long id);

    /**
     * Finds a score by id
     * @param id the ID to find
     * @return the score
     */
    public ScoreDTO findById(Long id);

    /**
     * Lists score statistics for the player
     * @param playerId player id to search by
     * @param gameId game id to search by
     * @return list of scores
     */
    public List<ScoreDTO> findByPlayerGame(Long playerId, Long gameId);

    /**
     * Lists score statistics for the competition
     * @param competitionId id to search by
     * @return list of scores
     */
    public List<ScoreDTO> findByCompetition(Long competitionId);

}
