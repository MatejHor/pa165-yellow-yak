package cz.fi.muni.pa165.yellow_yak.controllers;

import cz.fi.muni.pa165.yellow_yak.ApiUris;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.yellow_yak.facade.CompetitionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
/**
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
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO findCompetition(@PathVariable("id") Long id) throws Exception {
        logger.debug("rest findCompetition({})", id);
        CompetitionDTO competition = competitionFacade.findById(id);
        if (competition == null) {
            throw new ResourceNotFoundException();
        }
        return competition;
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
        List<CompetitionDTO> competitions = competitionFacade.findByGame(gameId);
        if (competitions.size() < 1) {
            throw new ResourceNotFoundException();
        }
        return competitions;
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
            throw new ResourceNotFoundException();
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
            throw new ResourceAlreadyExistingException();
        }
    }
}