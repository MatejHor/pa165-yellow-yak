package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Player;

import java.util.List;

/**
 * @author Lukas Mikula
 */
public interface PlayerService {

    /**
     * Creates a new player
     * @param name player name
     * @param email player email
     * @return the created player
     */
    public Player create(String name, String email);

    /**
     * Removes the player
     * @param id id to remove
     */
    public void remove(Long id);

    /**
     * Finds a player by id
     * @param id the ID to find
     * @return the player
     */
    public Player findById(Long id);

    /**
     * Lists players by username
     * @param username the name to find
     * @return the player
     */
    public List<Player> listByUsername(String username);

    /**
     * Finds players by team
     * @param teamId the team's ID
     * @return the players found
     */
    public List<Player> listByTeam(Long teamId);

    /**
     * Lists all players
     * @return players
     */
    public List<Player> listAll();

}
