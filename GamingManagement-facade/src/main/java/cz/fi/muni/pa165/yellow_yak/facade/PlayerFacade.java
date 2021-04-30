package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;

import javax.validation.constraints.NotNull;
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
    public PlayerDTO create(@NotNull String name, @NotNull String email);

    /**
     * Removes the player
     * @param id id to remove
     */
    public void remove(@NotNull Long id);

    /**
     * Finds a player by id
     * @param id the ID to find
     * @return the player
     */
    public PlayerDTO findById(@NotNull Long id);

    /**
     * Lists players by username
     * @param username the username to find
     * @return the player list
     */
    public List<PlayerDTO> findByUsername(@NotNull String username);

    /**
     * Finds players by team
     * @param teamId the team's ID
     * @return the players found
     */
    public List<PlayerDTO> findByTeam(@NotNull Long teamId);

    /**
     * Finds players
     * @return the players found
     */
    public List<PlayerDTO> findAll();
}
