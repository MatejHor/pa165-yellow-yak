package cz.fi.muni.pa165.yellow_yak.controllers;

import cz.fi.muni.pa165.yellow_yak.ApiUris;
import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;
import cz.fi.muni.pa165.yellow_yak.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.yellow_yak.facade.ScoreFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * REST layer for Score
 *
 * @author Matej Knazik
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_SCORES)
public class ScoreController {

    final static Logger logger = LoggerFactory.getLogger(ScoreController.class);

    @Autowired
    private ScoreFacade scoreFacade;

    /**
     * getting score based on score identification
     *
     * @param id score identifier
     * @return ScoreDTO
     * @throws ResourceNotFoundException
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final ScoreDTO findScore(@PathVariable("id") Long id) throws ResourceNotFoundException, InvalidParameterException {
        logger.debug("rest findScore({})", id);
        ScoreDTO score = null;
        try {
            score = scoreFacade.findById(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
        if (score == null) {
            throw new ResourceNotFoundException();
        }
        return score;
    }

    /**
     * getting scores based on competition
     *
     * @param competitionId competition identifier
     *
     * @return List of ScoreDTO
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/findBy/CompetitionId/{competitionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<ScoreDTO> findScoreByCompetition(@PathVariable("competitionId") Long competitionId) throws InvalidParameterException {
        logger.debug("rest findScoreByCompetition({})", competitionId);
        List<ScoreDTO> scores = new ArrayList<ScoreDTO>();
        try {
            scores = scoreFacade.findByCompetition(competitionId);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
        return scores;
    }

    /**
     * getting scores based on game and player
     *
     * @param gameId game identifier
     * @param playerId player identifier
     *
     * @return List of ScoreDTO
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/findBy/GameId/{gameId}/PlayerId/{playerId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<ScoreDTO> findScoreByGameAndPlayer(@PathVariable("gameId") Long gameId, @PathVariable("playerId") Long playerId) throws InvalidParameterException {
        logger.debug("rest findScoreByGameAndPlayer({}, {})", gameId, playerId);
        List<ScoreDTO> scores = new ArrayList<ScoreDTO>();
        try {
            scores = scoreFacade.findByPlayerGame(gameId, playerId);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
        return scores;
    }

    /**
     * remove score based on id
     *
     * @param id score identifier
     * @return whenever the operation was successful
     * @throws ResourceNotFoundException
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Boolean removeScore(@PathVariable("id") Long id) throws ResourceNotFoundException, InvalidParameterException {
        logger.debug("rest removeScore({})", id);
        try {
            return scoreFacade.remove(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * set result
     *
     * @param score score with score identifier and result
     *
     * @return ScoreDTO
     * @throws ResourceAlreadyExistingException
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/result", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ScoreDTO setResult(@RequestBody ScoreDTO score) throws ResourceAlreadyExistingException, IllegalArgumentException {
        logger.debug("rest setResult()");
        try {
            return scoreFacade.setResult(score.getId(), score.getResult());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * create score
     *
     * @param score score with competition id and player id
     * @return ScoreDTO
     * @throws ResourceAlreadyExistingException
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final ScoreDTO createScore(@RequestBody ScoreDTO score) throws ResourceAlreadyExistingException, IllegalArgumentException {
        logger.debug("rest createScore()");
        try {
            return scoreFacade.create(score.getCompetition().getId(), score.getPlayer().getId());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException();
        }
    }
}
