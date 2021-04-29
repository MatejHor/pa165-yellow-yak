package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matej Knazik
 */
public class TeamFacadeImpl implements TeamFacade {

    final static Logger log = LoggerFactory.getLogger(TeamFacadeImpl.class);

    @Inject
    private TeamService teamService;

    @Inject
    private BeanMappingService beanMappingService;

    @Override
    public TeamDTO create(String name) {
        log.info("create team, name = {}", name);
        return beanMappingService.mapTo(teamService.create(name), TeamDTO.class);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public TeamDTO findById(Long id) {
        return null;
    }

    @Override
    public void addPlayer(Long teamId, Long playerId) {

    }

    @Override
    public void removePlayer(Long teamId, Long playerId) {

    }

    @Override
    public List<TeamDTO> listByName(String name) {
        return null;
    }

    @Override
    public List<TeamDTO> listByCompetition(Long competitionId) {
        return null;
    }

    @Override
    public List<TeamDTO> listByPlayer(Long playerId) {
        return null;
    }
}
