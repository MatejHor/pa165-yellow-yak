package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
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
 * @author oreqizer, Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GameFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GameService gameService;

    @Mock
    private BeanMappingService beanMappingService;

    @Autowired
    @InjectMocks
    private GameFacade gameFacade;

    private Game game;
    private GameDTO gameDTO;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        this.gameFacade = new GameFacadeImpl(beanMappingService, gameService);
    }

    @BeforeMethod
    public void setup() {
        game = new Game();
        game.setId(1338L);
        game.setName("Battlefield 6");

        gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setName(game.getName());

        Mockito.doReturn(gameDTO).when(beanMappingService).mapTo(game, GameDTO.class);
        Mockito.doReturn(Collections.singletonList(gameDTO)).when(beanMappingService).mapTo(Collections.singletonList(game), GameDTO.class);
    }

    @Test
    public void create() {
        Mockito.doReturn(game).when(gameService).create(game.getName());

        Assert.assertEquals(gameFacade.create(game.getName()), gameDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNull() {
        gameFacade.create(null);
    }

    @Test
    public void remove() {
        Mockito.doReturn(true).when(gameService).remove(game.getId());

        Assert.assertTrue(gameService.remove(game.getId()));
    }

    @Test
    public void removeNotExisting() {
        Mockito.doReturn(true).when(gameService).remove(1330L);

        Assert.assertTrue(gameService.remove(1330L));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNull() {
        gameFacade.remove(null);
    }

    @Test
    public void findById() {
        Mockito.doReturn(game).when(gameService).findById(game.getId());

        Assert.assertEquals(gameFacade.findById(game.getId()), gameDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull() {
        gameFacade.findById(null);
    }

    @Test
    public void findByName() {
        Mockito.doReturn(Collections.singletonList(game)).when(gameService).findByName(game.getName());

        Assert.assertEquals(gameFacade.findByName(game.getName()), Collections.singletonList(gameDTO));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNameNull() {
        gameFacade.findByName(null);
    }

    @Test
    public void findAll() {
        Mockito.doReturn(Collections.singletonList(game)).when(gameService).findAll();

        Assert.assertEquals(gameFacade.findAll(), Collections.singletonList(gameDTO));
    }

}
