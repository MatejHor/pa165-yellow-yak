package cz.fi.muni.pa165.yellow_yak.facade;

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
}
