package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author oreqizer
 */
public interface PlayerFacade {

    /**
     * Creates a new player
     * @param name player name
     * @param email player email
     * @return the created player
     */
    public PlayerDTO create(String name, String email);

    /**
     * Removes the player
     * @param p player to remove
     */
    public void remove(PlayerDTO p);

    /**
     * Finds a player by name
     * @param name the name to find
     * @return the player
     */
    public PlayerDTO findByName(String name);

    /**
     * Finds a player by email
     * @param email the email to find
     * @return the player
     */
    public PlayerDTO findByEmail(String email);

    /**
     * Finds players by team
     * @param teamId the team's ID
     * @return the players found
     */
    public List<PlayerDTO> listByTeam(Long teamId);

}
