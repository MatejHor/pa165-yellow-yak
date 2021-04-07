package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Team;

import java.util.Date;
import java.util.List;

/**
 * @author Matej Knazik
 */
public interface TeamDao {

    /**
     * Create new team
     *
     * @param team team entity
     */
    void create(Team team);

    /**
     * Remove team
     *
     * @param team team entity
     */
    void remove(Team team);

    /**
     * Update existing team
     *
     * @param team team entity
     */
    void update(Team team);

    /**
     * Get all teams from the database
     *
     * @return list of all teams from the database
     */
    List<Team> getAll();

    /**
     * Get team by an id
     *
     * @param id team id of desired team
     * @return list of all teams from the database
     */
    Team getById(Long id);

    /**
     * Lists teams by a name
     *
     * @param name team's name to filter by
     * @return list of teams with specified name
     */
    List<Team> getByName(String name);

    /**
     * Lists teams by a date of their creation
     *
     * @param createdAt date to filter by
     * @return list of teams with specified creation date
     */
    List<Team> getByCreatedAt(Date createdAt);
}