package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;

import java.util.List;

/**
 * @author matho
 */
public interface CompetitionService {

    /**
     * Creates a new competition
     * @param gameId ID of the game
     * @param name competition name
     * @return the created competition
     */
    public Competition create(Long gameId, String name);

    /**
     * Removes the competition
     * @param competitionId competition ID to remove
     */
    public void remove(Long competitionId);

    /**
     * Finds a competition by name
     * @param name the name to find
     * @return the competition
     */
    public Competition findByName(String name);

    /**
     * Returns all competitions for this game
     * @param gameId the game to filter by
     * @return list of competitions
     */
    public List<Competition> listByGame(Long gameId);

}
