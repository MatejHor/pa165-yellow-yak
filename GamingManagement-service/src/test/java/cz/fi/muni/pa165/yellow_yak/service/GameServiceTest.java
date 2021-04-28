package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
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
 * @author Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class GameServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private GameDao gameDao;

    @Autowired
    @InjectMocks
    private  GameService gameService;

    private Game game;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        game = new Game();
        game.setName("TestGame");
    }

    @Test
    public void create() {
        String name = "Battlefield 6";
        Game game = gameService.create(name);

        Game gameWant = new Game();
        gameWant.setName(name);
        Assert.assertEquals(game, gameWant);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void createNull() {
        doThrow(NullPointerException.class).when(gameDao).create(null);

        gameService.create(null);
    }

    @Test
    public void remove() {
        doThrow(NullPointerException.class).when(gameDao).remove(null);

        gameService.remove(game.getId());

        Assert.assertNull(gameDao.findById(game.getId()));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeNull() {
        doThrow(NullPointerException.class).when(gameDao).remove(null);

        gameService.remove(null);
    }

    @Test
    public void findByIdExisting() {
        when(gameDao.findById(1L)).thenReturn(game);

        Game gameTest = gameService.findById(1L);

        Assert.assertEquals(gameTest, game);
    }

    @Test
    public void findByIdNonExisting() {
        when(gameDao.findById(2L)).thenReturn(null);

        Game gameTest = gameService.findById(2L);

        Assert.assertNull(gameTest);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void findByIdNull() {
        when(gameDao.findById(null)).thenThrow(NullPointerException.class);

        gameService.findById(null);
    }

    @Test
    public void listByName() {
        when(gameDao.findByName("Game")).thenReturn(Collections.singletonList(game));

        List<Game> list = gameService.listByName("Game");

        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0), game);
    }

    @Test
    public void listAll() {
        when(gameDao.findAll()).thenReturn(Collections.singletonList(game));

        List<Game> gameTestList = gameService.listAll();

        Assert.assertNotNull(gameTestList);
        Assert.assertEquals(gameTestList.size(), 1);
        Game gameTest = gameTestList.get(0);
        Assert.assertEquals(gameTest, game);
    }
}
