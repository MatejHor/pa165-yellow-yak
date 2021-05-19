package cz.fi.muni.pa165.yellow_yak.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.yellow_yak.ApiUris;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.yellow_yak.facade.CompetitionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Lukas Mikula
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
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO findCompetition(@PathVariable("id") Long id) throws Exception {
        logger.debug("rest findCompetition({})", id);
        try {
            return competitionFacade.findById(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * getting competition according to game
     *
     * @param gameId competition gameId
     * @return CompetitionDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/game/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CompetitionDTO> findCompetitionByGame(@PathVariable("gameId") Long gameId) throws Exception {
        logger.debug("rest findCompetitionByGame({})", gameId);
        try {
            return competitionFacade.findByGame(gameId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * remove competition according to id
     *
     * @param id competition identifier
     * @return if operation was successful
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Boolean removeCompetition(@PathVariable("id") Long id) throws Exception {
        logger.debug("rest removeCompetition({})", id);
        try {
            return competitionFacade.remove(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return false;
        }
    }

    /**
     * create competition
     *
     * @param competition competition's name
     * @return GameDTO
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO createGame(@RequestBody CompetitionDTO competition) throws Exception {
        logger.debug("rest createGame()");
        try {
            return competitionFacade.create(competition.getGame().getId(), competition.getName());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
