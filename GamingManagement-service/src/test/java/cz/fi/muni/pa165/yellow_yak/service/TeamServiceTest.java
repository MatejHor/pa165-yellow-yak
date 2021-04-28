package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
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

import static org.mockito.Mockito.*;

/**
 * @author oreqizer
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TeamDao teamDao;
    @Mock
    private PlayerDao playerDao;
    @Mock
    private CompetitionDao competitionDao;

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

    @Test
    public void addPlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setUsername("xxx_BILLY_xxx");
        player.setEmail("billy@killer.com");

        // TODO check it was called...?
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addPlayerNullTeam() {
        Player player = new Player();
        player.setId(1L);
        player.setUsername("xxx_BILLY_xxx");
        player.setEmail("billy@killer.com");

        when(playerDao.findById(player.getId())).thenReturn(player);
        when(teamDao.getById(team.getId())).thenReturn(team);
        doThrow(NullPointerException.class).when(teamDao).addPlayer(null, player);

        teamService.addPlayer(null, player.getId());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void addPlayerNullPlayer() {
        doThrow(NullPointerException.class).when(teamDao).addPlayer(team, null);

        teamService.addPlayer(team.getId(), null);
    }

    @Test
    public void removePlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setUsername("xxx_BILLY_xxx");
        player.setEmail("billy@killer.com");

        // TODO check it was called...?
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removePlayerNullTeam() {
        Player player = new Player();
        player.setId(1L);
        player.setUsername("xxx_BILLY_xxx");
        player.setEmail("billy@killer.com");

        when(playerDao.findById(player.getId())).thenReturn(player);
        when(teamDao.getById(team.getId())).thenReturn(team);
        doThrow(NullPointerException.class).when(teamDao).removePlayer(null, player);

        teamService.removePlayer(null, player.getId());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removePlayerNullPlayer() {
        doThrow(NullPointerException.class).when(teamDao).removePlayer(team, null);

        teamService.removePlayer(team.getId(), null);
    }

    @Test
    public void listByName() {
        when(teamDao.getByName(team.getName())).thenReturn(Collections.singletonList(team));

        List<Team> list = teamService.listByName(team.getName());

        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), team);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void listByNameNull() {
        doThrow(NullPointerException.class).when(teamDao).getByName(null);

        teamService.listByName(null);
    }

    @Test
    public void listByCompetition() {
        Competition competition = new Competition();
        competition.setId(1L);
        competition.setName("YOLOFEST");

        when(competitionDao.findById(competition.getId())).thenReturn(competition);
        when(teamDao.getByCompetition(competition)).thenReturn(Collections.singletonList(team));

        List<Team> list = teamService.listByCompetition(competition.getId());

        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), team);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void listByCompetitionNull() {
        doThrow(NullPointerException.class).when(teamDao).getByCompetition(null);

        teamService.listByCompetition(null);
    }

    @Test
    public void listByPlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setUsername("xxx_BILLY_xxx");
        player.setEmail("billy@killer.com");

        when(playerDao.findById(player.getId())).thenReturn(player);
        when(teamDao.getByPlayer(player)).thenReturn(Collections.singletonList(team));

        List<Team> list = teamService.listByPlayer(player.getId());

        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), team);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void listByPlayerNull() {
        doThrow(NullPointerException.class).when(teamDao).getByPlayer(null);

        teamService.listByPlayer(null);
    }

}
