package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.entity.Player;

import java.util.List;

/**
 * @author Lukas Mikula
 */
public interface PlayerService {
    public List<Player> findAll();
    public Player find(Long playerId);
}
