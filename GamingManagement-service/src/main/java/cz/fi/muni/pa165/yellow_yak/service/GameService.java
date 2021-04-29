package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

/**
 * @author Lukas Mikula
 */
public interface GameService {
    public Game create(String name);
    public void remove(Long id);
    public Game find(Long gameId);
    public List<Game> findAll();
    public List<Game> findByName(String name);
}
