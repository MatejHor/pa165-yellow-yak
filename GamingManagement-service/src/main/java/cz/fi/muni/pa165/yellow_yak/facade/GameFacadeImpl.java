package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Lukas Mikula
 */
@Service
@Transactional
public class GameFacadeImpl implements GameFacade{
    final static Logger log = LoggerFactory.getLogger(ScoreFacadeImpl.class);

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private GameService gameService;

    @Autowired
    public GameFacadeImpl(BeanMappingService beanMappingService, GameService gameService) {
        this.beanMappingService = beanMappingService;
        this.gameService = gameService;
    }

    @Override
    public GameDTO create(String name) {
        if (name == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("creating game, name = {}", name);
        return beanMappingService.mapTo(gameService.create(name), GameDTO.class);
    }

    @Override
    public void remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("removing game, id = {}", id);
        gameService.remove(id);
    }

    @Override
    public GameDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("finding game by ID, id = {}", id);
        return beanMappingService.mapTo(gameService.findById(id), GameDTO.class);
    }

    @Override
    public List<GameDTO> findByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("listing game by name, name = {}", name);
        return beanMappingService.mapTo(gameService.findByName(name), GameDTO.class);
    }

    @Override
    public List<GameDTO> findAll() {
        log.info("Get all Game");
        return beanMappingService.mapTo(gameService.findAll(), GameDTO.class);
    }
}
