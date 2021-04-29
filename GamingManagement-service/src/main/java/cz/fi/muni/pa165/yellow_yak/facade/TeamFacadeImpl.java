package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.TeamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Matej Knazik
 */
@Service
@Transactional
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
    public void remove(Long teamId) {
        log.info("removing team, id = {}", teamId);
        teamService.remove(teamId);
    }

    @Override
    public TeamDTO findById(Long teamId) {
        log.info("finding team by ID, id = {}", teamId);
        return beanMappingService.mapTo(teamService.findById(teamId), TeamDTO.class);
    }

    @Override
    public List<TeamDTO> findByName(String name) {
        log.info("finding teams by name, name = {}", name);
        return beanMappingService.mapTo(teamService.findByName(name), TeamDTO.class);
    }
}
