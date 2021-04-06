package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Team;

import java.util.Date;
import java.util.List;

/**
 * @author Matej Knazik
 */
public interface TeamDao {
    void create(Team team);

    void remove(Team team);

    void update(Team team);

    List<Team> getAll();

    Team getById(Long id);

    List<Team> getByName(String name);

    List<Team> getByCreatedAt(Date createdAt);

    List<Team> getByGame(Game game);
}
