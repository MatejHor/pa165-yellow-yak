package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.service.PlayerService;
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
 * @author oreqizer
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PlayerService playerService;

    @Autowired
    @InjectMocks
    private PlayerFacade playerFacade;

    private Player player;
    private PlayerDTO playerDTO;

    @BeforeClass
    public void init() throws ServiceException {
        // TODO playerService not being mocked
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        player = new Player();
        player.setId(1338L);
        player.setUsername("xxx_BILLY_xxx");
        player.setEmail("lol@kek.bur");

        playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setUsername(player.getUsername());
        playerDTO.setEmail(player.getEmail());
    }

    @Test
    public void create() {
//        Mockito.doReturn(player).when(playerService).create(player.getUsername(), player.getEmail());
//
//        Assert.assertEquals(playerFacade.create(player.getUsername(), player.getEmail()), playerDTO);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullUsername() {
        playerFacade.create(null, player.getEmail());
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNullEmail() {
        playerFacade.create(player.getUsername(), null);
    }

    @Test
    public void remove() {
//        playerFacade.remove(1337L);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeNull() {
        playerFacade.remove(null);
    }

    @Test
    public void findById() {
//        Mockito.doReturn(player).when(playerService).findById(player.getId());
//
//        Assert.assertEquals(playerFacade.findById(player.getId()), playerDTO);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByIdNull() {
        playerFacade.findById(null);
    }

    @Test
    public void findByUsername() {
//        Mockito.doReturn(player).when(playerService).findByUsername(player.getUsername());
//
//        Assert.assertEquals(playerFacade.findByUsername(player.getUsername()), Collections.singletonList(playerDTO));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByUsernameNull() {
        playerFacade.findByUsername(null);
    }

    @Test
    public void findByTeam() {
//        Mockito.doReturn(player).when(playerService).findByTeam(1337L);
//
//        Assert.assertEquals(playerFacade.findByTeam(1337L), Collections.singletonList(playerDTO));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByTeamNull() {
        playerFacade.findByTeam(null);
    }

    @Test
    public void findAll() {
//        Mockito.doReturn(player).when(playerService).findAll();
//
//        Assert.assertEquals(playerFacade.findAll(), Collections.singletonList(playerDTO));
    }

}
