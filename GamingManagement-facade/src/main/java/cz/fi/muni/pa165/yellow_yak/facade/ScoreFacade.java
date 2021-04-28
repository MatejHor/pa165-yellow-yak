package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author matho
 */
public interface ScoreFacade {
    public List<PlayerDTO> getAllPlayer();
    public List<GameDTO> getAllGame();
    public List<ScoreDTO> getPlayerScore(Long playerId, Long gameId, LocalDateTime oldest);

    // TODO these need implementing / reviewing

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
    public List<ScoreDTO> listByPlayerGame(Long playerId, Long gameId);

    /**
     * Lists score statistics for the competition
     * @param competitionId id to search by
     * @return list of scores
     */
    public List<ScoreDTO> listByCompetition(Long competitionId);

}
