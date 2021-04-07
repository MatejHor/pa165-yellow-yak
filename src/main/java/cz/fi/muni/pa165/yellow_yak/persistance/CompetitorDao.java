package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Competitor;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author oreqizer
 */
public interface CompetitorDao {
    /**
     * Creates a new competitor
     * @param c competitor to create
     */
    void create(Competitor c);

    /**
     * Lists all competitors
     * @return list of all competitors
     */
    List<Competitor> findAll();

    /**
     * Finds a competitor by ID
     * @param id the competitor's ID
     * @return the found competitor
     */
    Competitor findById(Long id);

    /**
     * Removes a competitor
     * @param c competitor to remove
     */
    void remove(Competitor c);

    /**
     * Updates a competitor
     * @param c competitor to update
     */
    void update(Competitor c);

    /**
     * Lists competitors by competition
     * @param competition competition to find competitors by
     * @return list of competitors
     */
    List<Competitor> getByCompetition(Competition competition);

    /**
     * Lists competitors by team
     * @param team team to find competitors by
     * @return list of competitors
     */
    List<Competitor> getByTeam(Team team);
}
