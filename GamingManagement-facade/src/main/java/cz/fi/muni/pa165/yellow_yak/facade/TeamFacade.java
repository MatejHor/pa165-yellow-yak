package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;

import java.util.List;

/**
 * @author oreqizer
 */
public interface TeamFacade {

    /**
     * Creates a new team
     * @param name team name
     * @return the created team
     */
    public TeamDTO create(String name);

    /**
     * Adds a player to the team
     * @param teamId team id
     * @param playerId player id
     */
    public void addPlayer(Long teamId, Long playerId);

    /**
     * Removes a player from the team
     * @param teamId team id
     * @param playerId player id
     */
    public void removePlayer(Long teamId, Long playerId);

    /**
     * Removes the team
     * @param id id to remove
     */
    public void remove(Long id);

    /**
     * Finds a team by id
     * @param id the ID to find
     * @return the team
     */
    public TeamDTO findById(Long id);

    /**
     * Lists teams by name
     * @param name the name to find
     * @return the team list
     */
    public List<TeamDTO> listByName(String name);

    /**
     * Finds teams by competition
     * @param competitionId the competition's ID
     * @return the teams found
     */
    public List<TeamDTO> listByCompetition(Long competitionId);

    /**
     * Finds teams by player
     * @param playerId the player's ID
     * @return the teams found
     */
    public List<TeamDTO> listByPlayer(Long playerId);

}
