package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.TeamDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
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

/**
 * @author Matej Knazik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TeamService teamService;

    @Autowired
    @InjectMocks
    private TeamFacade teamFacade;

    private Team team;
    private TeamDTO teamDTO;

    @BeforeClass
    public void init() throws ServiceException {
        // TODO scoreService not being mocked
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        team = new Team();
        team.setId(1337L);
        team.setName("testTeam");

        teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());

        Mockito.doReturn(team).when(teamService).create(team.getName());
    }

    @Test
    public void create() {
//        Assert.assertEquals(teamFacade.create(team.getName()), teamDTO);
    }


}
