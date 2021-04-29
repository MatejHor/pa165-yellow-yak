package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.CompetitionService;
import cz.fi.muni.pa165.yellow_yak.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Matej Horniak
 */
@Service
@Transactional
public class ScoreFacadeImpl implements ScoreFacade {

    final static Logger log = LoggerFactory.getLogger(ScoreFacadeImpl.class);

    @Inject
    private ScoreService scoreService;

    @Inject
    private CompetitionService competitionService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public List<ScoreDTO> findByGamePlayerDate(Long playerId, Long gameId, LocalDateTime oldest) {
        List<Competition> competitions = competitionService.findByGame(gameId);
        log.info("Get competition by gameId(gameId=" + gameId +
                ", competition size=" + competitions.size() + ")");
        List<Score> scores = scoreService.findByPlayerAndCompetitionAndDate(
                playerId,
                competitions,
                oldest);
        log.info("Find all scores with PlayerId(playerId=" + playerId +
                ", scores size=" + scores.size() + ")");
        return beanMappingService.mapTo(scores, ScoreDTO.class);
    }

    @Override
    public ScoreDTO create(Long competitionId, Long playerId) {
        log.info("create score, competitionId = {}, playerId = {}", competitionId, playerId);
        return beanMappingService.mapTo(scoreService.create(competitionId, playerId), ScoreDTO.class);
    }

    @Override
    public void remove(Long id) {
        log.info("removing score, id = {}", id);
        scoreService.remove(id);
    }

    @Override
    public ScoreDTO setResult(Long id, int result) {
        log.info("setting result, id = {}, result = {}", id, result);
        return beanMappingService.mapTo(scoreService.setResult(id, result), ScoreDTO.class);
    }

    @Override
    public ScoreDTO findById(Long id) {
        log.info("finding player by ID, id = {}", id);
        return beanMappingService.mapTo(scoreService.findById(id), ScoreDTO.class);
    }

    @Override
    public List<ScoreDTO> findByPlayerGame(Long playerId, Long gameId) {
        log.info("finding score by ID, playerId = {}, gameId = {}", playerId, gameId);
        return beanMappingService.mapTo(scoreService.findByPlayerAndGame(playerId, gameId), ScoreDTO.class);
    }

    @Override
    public List<ScoreDTO> findByCompetition(Long competitionId) {
        log.info("finding score by ID, competitionId = {}", competitionId);
        return beanMappingService.mapTo(scoreService.findByCompetition(competitionId), ScoreDTO.class);
    }
}
