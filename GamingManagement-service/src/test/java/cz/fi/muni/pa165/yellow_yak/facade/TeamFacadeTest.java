package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.TeamService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * @author Matej Knazik, Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TeamService teamService;

    @Mock
    private BeanMappingService beanMappingService;

    @Autowired
    @InjectMocks
    private TeamFacade teamFacade;

    private Team team;
    private TeamDTO teamDTO;


    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        this.teamFacade = new TeamFacadeImpl(beanMappingService, teamService);
    }

    @BeforeMethod
    public void setup() {
        team = new Team();
        team.setId(1338L);
        team.setName("Faze");

        teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());

        Mockito.doReturn(teamDTO).when(beanMappingService).mapTo(team, TeamDTO.class);
        Mockito.doReturn(Collections.singletonList(teamDTO)).when(beanMappingService).mapTo(Collections.singletonList(team), TeamDTO.class);
    }

    @Test
    public void create() {
        Mockito.doReturn(team).when(teamService).create(team.getName());

        Assert.assertEquals(teamFacade.create(team.getName()), teamDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createTeamNullTest() {
        teamFacade.create(null);
    }

    @Test
    public void remove() {
        Mockito.doReturn(true).when(teamService).remove(team.getId());

        Assert.assertTrue(teamFacade.remove(team.getId()));
    }

    @Test
    public void removeNotExisting() {
        Mockito.doReturn(true).when(teamService).remove(1336L);

        Assert.assertTrue(teamFacade.remove(1336L));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeTeamNullTest() {
        teamFacade.remove(null);
    }

    @Test
    public void findById() {
        Mockito.doReturn(team).when(teamService).findById(team.getId());

        Assert.assertEquals(teamFacade.findById(team.getId()), teamDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        teamFacade.findById(null);
    }

    @Test
    public void findByName() {
        Mockito.doReturn(Collections.singletonList(team)).when(teamService).findByName(team.getName());

        Assert.assertEquals(teamFacade.findByName(team.getName()), Collections.singletonList(teamDTO));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNameNullTest() {
        teamFacade.findByName(null);
    }


}
