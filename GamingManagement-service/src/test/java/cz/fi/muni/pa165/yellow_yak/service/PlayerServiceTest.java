package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * Tests for player service layer
 *
 * @author Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PlayerDao playerDao;
    @Mock
    private TeamDao teamDao;

    @Autowired
    @InjectMocks
    private  PlayerService playerService;

    private Player player;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createPlayerInit() {
        player = new Player();
        player.setUsername("blazeit_michael");
        player.setEmail("kek@lol.bur");
    }

    @Test
    public void createPlayer() {
        Player p = new Player();
        p.setUsername("xxx_BILLY_xxx");
        p.setEmail("lol@kek.bur");
        p.setCreatedAt(LocalDate.now());

        Player res = playerService.create("xxx_BILLY_xxx", "lol@kek.bur", null);

        Assert.assertEquals(res, p);
    }

    @Test
    public void removePlayer() {
        Long id = player.getId();
        boolean success = playerService.remove(id);
        Assert.assertNull(playerService.findById(id));
        Assert.assertTrue(success);
    }

    @Test
    public void findByIdPlayer() {
        Mockito.doReturn(player).when(playerDao).findById(1L);

        Player res = playerService.findById(1L);

        Assert.assertEquals(player, res);
    }

    @Test
    public void findByIdExisting() {
        when(playerDao.findById(1L)).thenReturn(player);

        Player playerTest = playerService.findById(1L);

        Assert.assertEquals(playerTest, player);
    }

    @Test
    public void findByIdNonExisting() {
        when(playerDao.findById(2L)).thenReturn(null);

        Player playerTest = playerService.findById(2L);

        Assert.assertNull(playerTest);
    }

    @Test
    public void findByIdNull() {
        when(playerDao.findById(null)).thenReturn(null);

        Player playerTest = playerService.findById(null);

        Assert.assertNull(playerTest);
    }

    @Test
    public void findByUsername() {
        when(playerDao.findByUsername(player.getUsername())).thenReturn(Collections.singletonList(player));

        List<Player> playerTestList = playerService.findByUsername(player.getUsername());

        Assert.assertNotNull(playerTestList);
        Assert.assertEquals(playerTestList.size(), 1);
        Assert.assertEquals(playerTestList.get(0), player);
    }

    @Test
    public void findByTeam() {
        Team team = new Team();
        team.setId(1337L);
        team.setName("kekegas");

        when(teamDao.findById(team.getId())).thenReturn(team);
        when(playerDao.findByTeam(team)).thenReturn(Collections.singletonList(player));

        List<Player> playerTestList = playerService.findByTeam(team.getId());

        Assert.assertNotNull(playerTestList);
        Assert.assertEquals(playerTestList.size(), 1);
        Assert.assertEquals(playerTestList.get(0), player);
    }

    @Test
    public void findAll() {
        when(playerDao.findAll()).thenReturn(Collections.singletonList(player));

        List<Player> playerTestList = playerService.findAll();

        Assert.assertNotNull(playerTestList);
        Assert.assertEquals(playerTestList.size(), 1);
        Assert.assertEquals(playerTestList.get(0), player);
    }
}
