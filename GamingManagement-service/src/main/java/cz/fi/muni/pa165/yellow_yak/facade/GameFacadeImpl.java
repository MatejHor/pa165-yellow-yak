package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.GameService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

public class GameFacadeImpl implements GameFacade{
    final static Logger log = LoggerFactory.getLogger(ScoreFacadeImpl.class);

    @Inject
    private BeanMappingService beanMappingService;

    @Inject
    private GameService gameService;

    @Override
    public GameDTO create(String name) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public GameDTO findById(Long id) {
        return null;
    }

    @Override
    public List<GameDTO> findByName(String name) {
        return null;
    }

    @Override
    public List<GameDTO> findAll() {
        log.info("Get all Game");
        return beanMappingService.mapTo(gameService.findAll(), GameDTO.class);
    }
}
