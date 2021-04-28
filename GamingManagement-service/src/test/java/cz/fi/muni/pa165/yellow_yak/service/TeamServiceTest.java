package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * @author oreqizer
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TeamDao teamDao;

    @Autowired
    @InjectMocks
    private  TeamService teamService;

    private Team team;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        team = new Team();
        team.setName("1337s");
    }

    @Test
    public void create() {
        String name = "Teamizer";
        Team team = teamService.create(name);

        Team teamWant = new Team();
        teamWant.setName(name);
        Assert.assertEquals(team, teamWant);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNull() {
        doThrow(NullPointerException.class).when(teamDao).create(null);

        teamService.create(null);
    }

    @Test
    public void remove() {
        doThrow(NullPointerException.class).when(teamDao).remove(null);

        teamService.remove(team.getId());

        Assert.assertNull(teamDao.getById(team.getId()));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeNull() {
        doThrow(NullPointerException.class).when(teamDao).remove(null);

        teamService.remove(null);
    }

    @Test
    public void findByIdExisting() {
        when(teamDao.getById(1L)).thenReturn(team);

        Team teamTest = teamService.findById(1L);

        Assert.assertEquals(teamTest, team);
    }

    @Test
    public void findByIdNonExisting() {
        when(teamDao.getById(2L)).thenReturn(null);

        Team teamTest = teamService.findById(2L);

        Assert.assertNull(teamTest);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByIdNull() {
        when(teamDao.getById(null)).thenThrow(NullPointerException.class);

        Team teamTest = teamService.findById(null);

        Assert.assertNull(teamTest);
    }

    // TODO:
    // - addPlayer
    // - removePlayer
    // - listByName
    // - listByCompetition
    // - listByPlayer
}
