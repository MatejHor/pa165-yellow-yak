package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

/**
 * CompetitionDao performs CRUD operations for entity Competition.
 *
 * @author Lukas Mikula
 */
public interface CompetitionDao {

    /**
     * Creates Competition into the database
     *
     * @param c Competition entity, which we want to store in DB
     */
    void create(Competition c);

    /**
     * Finds all Competitions from the DB
     *
     * @return list of all Competitions which were found
     */
    List<Competition> findAll();

    /**
     * Finds specific Competition in the DB by Competition id
     *
     * @param id Competition id which we want to found
     * @return found Competition with required id
     */
    Competition findById(Long id);

    /**
     * Removes given Competition from the DB
     *
     * @param c Competition with we want to remove
     */
    void remove(Competition c);

    /** Updates given Competition into the DB
     *
     * @param c Competition which we want to update
     */
    void update(Competition c);

    /**
     * Finds all Competitions by it's name
     *
     * @param name Name of the Competitions, we want to find
     * @return List of all Competitions which have same name as we provided
     */
    List<Competition> findByName(String name) ;

    /**
     * Finds all Competitions by game Id
     *
     * @param game Id of the Game, we want to find
     * @return List of all Competitions which have same name as we provided
     */
    List<Competition> findByGame(Game game) ;

    /**
     * Finds specific Competition in the DB which is oldest
     * @return found Competition
     */
    Competition findOldest();
}
