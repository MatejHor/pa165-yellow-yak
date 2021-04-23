package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Game;

import java.util.List;

public interface GameService {
    public List<Game> findAll();
    public Game find(Long gameId);
}
