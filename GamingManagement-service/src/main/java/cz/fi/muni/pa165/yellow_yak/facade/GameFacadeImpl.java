package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Lukas Mikula
 */
public class GameFacadeImpl implements GameFacade{
    final static Logger log = LoggerFactory.getLogger(ScoreFacadeImpl.class);

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private GameService gameService;

    @Override
    public GameDTO create(String name) {
        log.info("creating game, name = {}", name);
        return beanMappingService.mapTo(gameService.create(name), GameDTO.class);
    }

    @Override
    public void remove(Long id) {
        log.info("removing game, id = {}", id);
        gameService.remove(id);
    }

    @Override
    public GameDTO findById(Long id) {
        log.info("finding game by ID, id = {}", id);
        return beanMappingService.mapTo(gameService.find(id), GameDTO.class);
    }

    @Override
    public List<GameDTO> findByName(String name) {
        log.info("listing game by name, name = {}", name);
        return beanMappingService.mapTo(gameService.findByName(name), GameDTO.class);
    }

    @Override
    public List<GameDTO> findAll() {
        log.info("Get all Game");
        return beanMappingService.mapTo(gameService.findAll(), GameDTO.class);
    }
}
