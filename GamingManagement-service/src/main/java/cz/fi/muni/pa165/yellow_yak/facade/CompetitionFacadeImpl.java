package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.CompetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author oreqizer
 */
@Service
@Transactional
public class CompetitionFacadeImpl implements CompetitionFacade {

    final static Logger log = LoggerFactory.getLogger(ScoreFacadeImpl.class);

    private final BeanMappingService beanMappingService;

    private final CompetitionService competitionService;

    @Autowired
    public CompetitionFacadeImpl(BeanMappingService beanMappingService, CompetitionService competitionService) {
        this.beanMappingService = beanMappingService;
        this.competitionService = competitionService;
    }

    @Override
    public CompetitionDTO create(Long gameId, String name) {
        if (gameId == null || name == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("creating competition, gameId = {}, name = {}", gameId, name);
        return beanMappingService.mapTo(competitionService.create(gameId, name), CompetitionDTO.class);
    }

    @Override
    public boolean remove(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("removing competition, id = {}", id);
        return competitionService.remove(id);
    }

    @Override
    public CompetitionDTO findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("finding competition by ID, id = {}", id);
        return beanMappingService.mapTo(competitionService.findById(id), CompetitionDTO.class);
    }

    @Override
    // TODO D1LL1G4F
    public List<CompetitionDTO> findByGame(Long gameId) {
        if (gameId == null) {
            throw new IllegalArgumentException("arguments cannot be null");
        }
        log.info("listing competition by game ID, gameId = {}", gameId);
        return beanMappingService.mapTo(competitionService.findByGame(gameId), CompetitionDTO.class);
    }

}
