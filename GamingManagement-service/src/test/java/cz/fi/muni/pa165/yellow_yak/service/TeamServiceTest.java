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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Matej Knazik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TeamDao teamDao;

    private Team testTeam;
    private Team testTeam2;

    @Autowired
    @InjectMocks
    private TeamService teamService;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createScore() {
        Team newTeam = new Team();
        newTeam.setName("TestTeam1");
        newTeam.setId(42L);

        Team newTeam2 = new Team();
        newTeam2.setName(newTeam.getName());
        newTeam2.setId(1337L);

        testTeam = newTeam;
        testTeam2 = newTeam2;

        when(teamDao.findById(testTeam.getId())).thenReturn(testTeam);
        when(teamDao.findById(testTeam2.getId())).thenReturn(testTeam2);
        when(teamDao.findById(null)).thenReturn(null);

        when(teamDao.findByName(testTeam.getName())).thenReturn(Arrays.asList(testTeam, testTeam2));
        when(teamDao.findByName(testTeam.getName())).thenReturn(Arrays.asList(testTeam, testTeam2));
        when(teamDao.findByName(null)).thenReturn(Collections.emptyList());

        when(teamDao.findAll()).thenReturn(Arrays.asList(testTeam, testTeam2));
    }

    @Test
    public void createTest() {
        String testTeamName = "Test team 2";
        Team testTeam = teamService.create(testTeamName);

        Assert.assertNotNull(testTeam);
        Assert.assertEquals(testTeam.getName(), testTeamName);
    }

    @Test
    public void createNullTest() {
        Team testTeam = teamService.create(null);
        Assert.assertNull(testTeam);
    }

    @Test
    public void removeTest() {
        Long testTeamId = testTeam.getId();
        Team foundTestTeam = teamService.findById(testTeamId);
        Assert.assertEquals(foundTestTeam, testTeam);

        teamService.remove(testTeamId);
        when(teamDao.findById(testTeamId)).thenReturn(null);

        foundTestTeam = teamService.findById(testTeamId);
        Assert.assertNull(foundTestTeam);
    }

    @Test
    public void removeNullTest() {
        teamService.remove(null);
    }

    @Test
    public void findByNameTest() {
        List<Team> foundTeams = teamService.findByName(testTeam.getName());
        Assert.assertEquals(foundTeams.size(),2);
        Assert.assertEquals(foundTeams, Arrays.asList(testTeam, testTeam2));
    }

    @Test
    public void findByNameNullTest() {
        List<Team> foundTeams = teamService.findByName(null);
        Assert.assertEquals(foundTeams.size(),0);
        Assert.assertEquals(foundTeams,Collections.emptyList());
    }

    @Test
    public void findByIdTest() {
        Team foundTeam = teamService.findById(testTeam.getId());
        Assert.assertEquals(foundTeam, testTeam);
        foundTeam = teamService.findById(testTeam2.getId());
        Assert.assertEquals(foundTeam, testTeam2);
    }

    @Test
    public void findByIdNullTest() {
        Team foundTeam = teamService.findById(null);
        Assert.assertNull(foundTeam);
    }

    @Test
    public void findAllTest() {
        List<Team> foundTeams = teamService.findAll();
        Assert.assertEquals(foundTeams.size(),2);
        Assert.assertEquals(foundTeams, Arrays.asList(testTeam, testTeam2));
    }

}
