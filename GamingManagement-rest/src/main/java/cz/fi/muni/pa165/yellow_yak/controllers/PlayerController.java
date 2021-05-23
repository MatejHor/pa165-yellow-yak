package cz.fi.muni.pa165.yellow_yak.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import cz.fi.muni.pa165.yellow_yak.ApiUris;
import cz.fi.muni.pa165.yellow_yak.mixin.PlayerDTOMixin;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceAlreadyExistingException;
import cz.fi.muni.pa165.yellow_yak.exceptions.ResourceNotFoundException;
import cz.fi.muni.pa165.yellow_yak.facade.PlayerFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * @author Matej Horniak
 */
@RestController
@RequestMapping(ApiUris.ROOT_URI_PLAYERS)
public class PlayerController {

    final static Logger logger = LoggerFactory.getLogger(PlayerController.class);

    @Autowired
    private PlayerFacade playerFacade;

    /**
     * returns all players according to as JSON
     *
     * @return list of PlayerDTOs
     * @throws JsonProcessingException
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<PlayerDTO> findPlayers() throws JsonProcessingException {
        logger.debug("rest findPlayers()");
        return playerFacade.findAll();
    }

    /**
     * getting player according to id
     *
     * @param id player identifier
     * @return PlayerDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO findPlayer(@PathVariable("id") long id) throws Exception {
        logger.debug("rest findPlayer({})", id);
        PlayerDTO player = playerFacade.findById(id);
        if (player == null) {
            throw new ResourceNotFoundException();
        }
        return player;
    }

    /**
     * remove player according to id
     *
     * @param id player identifier
     * @return if operation was successful
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Boolean removePlayer(@PathVariable("id") long id) throws Exception {
        logger.debug("rest removePlayer({})", id);
        try {
            return playerFacade.remove(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * getting player according to username
     *
     * @param username player username
     * @return PlayerDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<PlayerDTO> findPlayerByUsername(@PathVariable("username") String username) throws Exception {
        logger.debug("rest findPlayerByUsername({})", username);
        List<PlayerDTO> players = playerFacade.findByUsername(username);
        if (players.size() < 1) {
            throw new ResourceNotFoundException();
        }
        return players;
    }

    /**
     * getting player according to team
     *
     * @param teamId team identifier
     * @return PlayerDTO
     * @throws ResourceNotFoundException
     */
    @RequestMapping(value = "/team/{teamId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<PlayerDTO> findPlayerByTeam(@PathVariable("team") Long teamId) throws Exception {
        logger.debug("rest findPlayerByTeam({})", teamId);
        List<PlayerDTO> players = playerFacade.findByTeam(teamId);
        if (players.size() < 1) {
            throw new ResourceNotFoundException();
        }
        return players;
    }

    /**
     * create player
     *
     * @param player player's username and email
     * @return PlayerDTO
     * @throws ResourceAlreadyExistingException
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO createPlayer(@RequestBody PlayerDTO player) throws Exception {
        logger.debug("rest createPlayer()");
        try {
            return playerFacade.create(player.getUsername(), player.getEmail());
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException();
        }
    }
}