package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation for player facade
 *
 * @author oreqizer
 */
@Service
@Transactional
public class PlayerFacadeImpl implements PlayerFacade {

    final static Logger log = LoggerFactory.getLogger(ScoreFacadeImpl.class);

    private final BeanMappingService beanMappingService;

    private final PlayerService playerService;

    @Autowired
    public PlayerFacadeImpl(BeanMappingService beanMappingService, PlayerService playerService) {
        this.beanMappingService = beanMappingService;
        this.playerService = playerService;
    }

    @Override
    public PlayerDTO create(String name, String email, Long teamId) {
        if (name == null || email == null || teamId == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (teamId <= 0 || name.isEmpty() || email.isEmpty()) {
            throw new IllegalArgumentException("invalid argument");
        }
        log.info("creating player, name = {}, email = {}, teamId = {}", name, email, teamId);
        return beanMappingService.mapTo(playerService.create(name, email, teamId), PlayerDTO.class);
    }

    @Override
    public boolean remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("id cannot be zero or negative value");
        }
        log.info("removing player, id = {}", id);
        return playerService.remove(id);
    }

    @Override
    public PlayerDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (id <= 0) {
            throw new IllegalArgumentException("id cannot be zero or negative value");
        }
        log.info("finding player by ID, id = {}", id);
        return beanMappingService.mapTo(playerService.findById(id), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> findByUsername(String username) {
        if (username == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (username.isEmpty()) {
            throw new IllegalArgumentException("invalid argument");
        }
        log.info("listing player by username, username = {}", username);
        return beanMappingService.mapTo(playerService.findByUsername(username), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> findByTeam(Long teamId) {
        if (teamId == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        if (teamId <= 0) {
            throw new IllegalArgumentException("teamId cannot be zero or negative value");
        }
        log.info("listing player by team, teamId = {}", teamId);
        return beanMappingService.mapTo(playerService.findByTeam(teamId), PlayerDTO.class);
    }

    @Override
    public List<PlayerDTO> findAll() {
        log.info("Get all Player");
        return beanMappingService.mapTo(playerService.findAll(), PlayerDTO.class);
    }
}
