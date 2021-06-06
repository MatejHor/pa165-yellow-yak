package cz.fi.muni.pa165.yellow_yak.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.yellow_yak.ApiUris;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.yellow_yak.facade.CompetitionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * REST layer for competition
 *
 * @author Matej Knazik
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_COMPETITIONS)
public class CompetitionController {

    final static Logger logger = LoggerFactory.getLogger(CompetitionController.class);

    @Autowired
    private CompetitionFacade competitionFacade;

    /**
     * getting competition according to id
     *
     * @param id competition identifier
     * @return CompetitionDTO
     * @throws ResourceNotFoundException
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO findCompetition(@PathVariable("id") Long id) throws ResourceNotFoundException, InvalidParameterException {
        logger.debug("rest findCompetition({})", id);
        CompetitionDTO competition = null;
        try {
            competition = competitionFacade.findById(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
        if (competition == null) {
            throw new ResourceNotFoundException();
        }
        return competition;
    }

    /**
     * returns all competitions according to as JSON
     *
     * @return list of CompetitionDTOs
     * @throws JsonProcessingException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CompetitionDTO> findCompetitions() throws JsonProcessingException {
        logger.debug("rest findGames()");
        return competitionFacade.findAll();
    }

    /**
     * getting competition according to game
     *
     * @param gameId competition gameId
     * @return CompetitionDTO
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CompetitionDTO> findCompetitionByGame(@PathVariable("gameId") Long gameId) throws InvalidParameterException {
        logger.debug("rest findCompetitionByGame({})", gameId);
        List<CompetitionDTO> competitions = new ArrayList<>();
        try {
            competitions = competitionFacade.findByGame(gameId);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
        return competitions;
    }

    /**
     * remove competition according to id
     *
     * @param id competition identifier
     * @return if operation was successful
     * @throws ResourceNotFoundException
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Boolean removeCompetition(@PathVariable("id") Long id) throws ResourceNotFoundException, InvalidParameterException {
        logger.debug("rest removeCompetition({})", id);
        try {
            return competitionFacade.remove(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * create competition
     *
     * @param competition competition's name
     * @return GameDTO
     * @throws ResourceAlreadyExistingException
     * @throws IllegalArgumentException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO createGame(@RequestBody CompetitionDTO competition) throws ResourceAlreadyExistingException, IllegalArgumentException {
        logger.debug("rest createGame()");
        try {
            return competitionFacade.create(competition.getGame().getId(), competition.getName());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException();
        }
    }
}