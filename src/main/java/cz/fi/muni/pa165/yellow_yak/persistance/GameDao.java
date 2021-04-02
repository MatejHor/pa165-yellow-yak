package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

/**
 * @author Matej Horniak
 */
public interface GameDao {

    void create(Game p);

    List<Game> findAll();

    Game findById(Long id);

    void remove(Game p);

    void update(Game p);

    List<Game> findByName(String name) ;
}
