package cz.fi.muni.pa165.yellow_yak.controllers;

import cz.fi.muni.pa165.yellow_yak.ApiUris;
import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;
import cz.fi.muni.pa165.yellow_yak.exceptions.InvalidParameterException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.yellow_yak.facade.TeamFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * REST layer for team
 *
 * @author oreqizer
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_TEAMS)
public class TeamController {

    final static Logger logger = LoggerFactory.getLogger(TeamController.class);

    @Autowired
    private TeamFacade teamFacade;

    /**
     * getting team according to id
     *
     * @param id team identifier
     * @return TeamDTO
     * @throws ResourceNotFoundException
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO findTeam(@PathVariable("id") long id) throws ResourceNotFoundException, InvalidParameterException {
        logger.debug("rest findTeam({})", id);
        TeamDTO team = null;
        try {
            team = teamFacade.findById(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
        if (team == null) {
            throw new ResourceNotFoundException();
        }
        return team;
    }

    /**
     * remove team according to id
     *
     * @param id team identifier
     * @return if operation was successful
     * @throws ResourceNotFoundException
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Boolean removeTeam(@PathVariable("id") long id) throws ResourceNotFoundException, InvalidParameterException {
        logger.debug("rest removeTeam({})", id);
        try {
            return teamFacade.remove(id);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * create team
     *
     * @param team team's name
     * @return TeamDTO
     * @throws ResourceAlreadyExistingException
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO createTeam(@RequestBody TeamDTO team) throws ResourceAlreadyExistingException, InvalidParameterException {
        logger.debug("rest createTeam()");
        try {
            return teamFacade.create(team.getName());
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException();
        }
    }

    /**
     * getting team according to name
     *
     * @param name team name
     * @return TeamDTO
     * @throws InvalidParameterException
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<TeamDTO> findTeamByName(@PathVariable("name") String name) throws InvalidParameterException {
        logger.debug("rest findTeamByName({})", name);
        List<TeamDTO> teams = new ArrayList<>();
        try {
            teams = teamFacade.findByName(name);
        } catch (IllegalArgumentException e) {
            throw new InvalidParameterException();
        }
        return teams;
    }
}