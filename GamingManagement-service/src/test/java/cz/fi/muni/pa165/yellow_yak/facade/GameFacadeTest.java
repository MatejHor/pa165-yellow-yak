package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.service.GameService;
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
public class GameFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GameService gameService;

    @Autowired
    @InjectMocks
    private GameFacade gameFacade;

    private Game game;
    private GameDTO gameDTO;

    @BeforeClass
    public void init() throws ServiceException {
        // TODO gameService not being mocked
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        game = new Game();
        game.setId(1338L);
        game.setName("Battlefield 6");

        gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setName(game.getName());
    }

    @Test
    public void create() {
//        Mockito.doReturn(game).when(gameService).create(game.getName());
//
//        Assert.assertEquals(gameFacade.create(game.getName()), gameDTO);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNull() {
        gameFacade.create(null);
    }

    @Test
    public void remove() {
//        gameFacade.remove(1337L);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeNull() {
        gameFacade.remove(null);
    }

    @Test
    public void findById() {
//        Mockito.doReturn(game).when(gameService).find(game.getId());
//
//        Assert.assertEquals(gameFacade.findById(game.getId()), gameDTO);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByIdNull() {
        gameFacade.findById(null);
    }

    @Test
    public void findByName() {
//        Mockito.doReturn(game).when(gameService).findByName(game.getName());
//
//        Assert.assertEquals(gameFacade.findByName(game.getName()), Collections.singletonList(gameDTO));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByNameNull() {
        gameFacade.findByName(null);
    }

    @Test
    public void findAll() {
//        Mockito.doReturn(game).when(gameService).findAll();
//
//        Assert.assertEquals(gameFacade.findAll(), Collections.singletonList(gameDTO));
    }

}
