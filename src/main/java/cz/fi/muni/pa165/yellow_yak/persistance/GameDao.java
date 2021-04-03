package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

/**
 * @author Matej Horniak
 */
public interface GameDao {

    void create(Game g);

    List<Game> findAll();

    Game findById(Long id);

    void remove(Game g);

    void update(Game g);

    Game findByName(String name) ;
}
