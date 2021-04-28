package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author matho
 */
@Service
@Transactional
public class ScoreFacadeImpl implements ScoreFacade {

    final static Logger log = LoggerFactory.getLogger(ScoreFacadeImpl.class);

    @Inject
    private PlayerService playerService;

    @Inject
    private GameService gameService;

    @Inject
    private ScoreService scoreService;

    @Inject
    private CompetitionService competitionService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public List<PlayerDTO> getAllPlayer() {
        log.info("Get all Player");
        return beanMappingService.mapTo(playerService.findAll(), PlayerDTO.class);
    }

    @Override
    public List<GameDTO> getAllGame() {
        log.info("Get all Game");
        return beanMappingService.mapTo(gameService.findAll(), GameDTO.class);
    }

    @Override
    public List<ScoreDTO> getPlayerScore(Long playerId, Long gameId, LocalDateTime oldest) {
        List<Competition> competitions = competitionService.listByGame(gameId);
        log.info("Get competition by gameId(gameId=" + gameId +
                ", competition size=" + competitions.size() + ")" );
        List<Score> scores = new ArrayList<>();

        for (Competition competition: competitions) {
            scores.addAll(scoreService.findByPlayerAndCompetitionAndDate(
                    playerId,
                    competition.getId(),
                    oldest
            ));
        }
        log.info("Find all scores with PlayerId(playerId=" + playerId +
                ", scores size=" + scores.size() + ")");
        return beanMappingService.mapTo(scores, ScoreDTO.class);
    }
}
