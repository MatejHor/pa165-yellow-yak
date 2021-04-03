package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;

import java.util.List;

/**
 * @author Lukas Mikula
 */
public interface CompetitionDao {
    void create(Competition c);

    List<Competition> findAll();

    Competition findById(Long id);

    void remove(Competition c);

    void update(Competition c);

    List<Competition> findByName(String name) ;
}
