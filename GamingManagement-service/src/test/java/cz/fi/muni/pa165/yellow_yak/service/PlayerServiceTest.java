package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
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

import static org.mockito.Mockito.when;

/**
 * @author Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PlayerDao playerDao;

    @Autowired
    @InjectMocks
    private  PlayerService playerService;

    private Player player;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createPlayer() {
        Player playerTest = new Player();
        playerTest.setUsername("TestPlayerName");
        playerTest.setEmail("TestPlayerEmail");

        player = playerTest;

        when(playerDao.findById(1L)).thenReturn(playerTest);
        when(playerDao.findById(null)).thenReturn(null);
        when(playerDao.findById(2L)).thenReturn(null);
        when(playerDao.findAll()).thenReturn(Collections.singletonList(playerTest));
    }

    @Test
    public void findByIdExisting() {
        Player playerTest = playerService.findById(1L);

        Assert.assertEquals(playerTest, player);
    }

    @Test
    public void findByIdNonExisting() {
        Player playerTest = playerService.findById(2L);

        Assert.assertNull(playerTest);
    }

    @Test
    public void findByIdNull() {
        Player playerTest = playerService.findById(null);

        Assert.assertNull(playerTest);
    }

    // TODO:
    // - create
    // - createNull
    // - remove
    // - removeNull
    // - listByUsername
    // - listByUsernameNull
    // - listByTeam
    // - listByTeamNull

    @Test
    public void findAll() {
        List<Player> playerTestList = playerService.listAll();

        Assert.assertNotNull(playerTestList);
        Assert.assertEquals(playerTestList.size(), 1);
        Player playerTest = playerTestList.get(0);
        Assert.assertEquals(playerTest, player);
    }
}
