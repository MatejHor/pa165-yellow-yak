package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Facade for Team
 *
 * @author oreqizer
 * @author Matej Knazik
 */
public interface TeamFacade {

    /**
     * Creates a new team
     * @param name team name
     * @return the newly created team, if no team was created returns null
     */
    public TeamDTO create(@NotNull String name);

    /**
     * Removes the team
     * @param teamId ID of a team to remove
     */
    public boolean remove(@NotNull Long teamId);

    /**
     * Finds a team by id
     * @param teamId the ID to find
     * @return the team or null when no team with specified ID
     */
    public TeamDTO findById(@NotNull Long teamId);

    /**
     * Lists teams by name
     * @param name the name to find
     * @return the list of teams with specified name
     */
    public List<TeamDTO> findByName(@NotNull String name);
}
