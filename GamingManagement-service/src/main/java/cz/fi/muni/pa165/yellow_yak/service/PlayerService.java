package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Player;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Lukas Mikula, oreqizer
 */
public interface PlayerService {

    /**
     * Creates a new player
     * @param name player name
     * @param email player email
     * @return the created player
     */
    public Player create(@NotNull String name, @NotNull String email);

    /**
     * Removes the player
     * @param id id to remove
     */
    public boolean remove(@NotNull Long id);

    /**
     * Finds a player by id
     * @param id the ID to find
     * @return the player
     */
    public Player findById(@NotNull Long id);

    /**
     * Lists players by username
     * @param username the name to find
     * @return the player
     */
    public List<Player> findByUsername(@NotNull String username);

    /**
     * Finds players by team
     * @param teamId the team's ID
     * @return the players found
     */
    public List<Player> findByTeam(@NotNull Long teamId);

    /**
     * Lists all players
     * @return players
     */
    public List<Player> findAll();

}
