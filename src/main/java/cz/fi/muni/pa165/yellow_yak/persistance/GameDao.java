package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

/**
 * Created by Matej Horniak on 1.4.2021.
 */
public interface GameDao {

    void create(Game p);

    List<Game> findAll();

    Game findById(Long id);

    void remove(Game p);

    void update(Game p);

    List<Game> findByName(String name) ;
}
