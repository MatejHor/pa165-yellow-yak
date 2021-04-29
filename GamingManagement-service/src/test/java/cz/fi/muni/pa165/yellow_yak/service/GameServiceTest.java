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
    public void createGame() {
        Game gameTest = new Game();
        gameTest.setName("TestGame");

        game = gameTest;

        when(gameDao.findById(1L)).thenReturn(gameTest);
        when(gameDao.findById(null)).thenReturn(null);
        when(gameDao.findById(2L)).thenReturn(null);
        when(gameDao.findAll()).thenReturn(Collections.singletonList(gameTest));
        when(gameDao.findByName("TestGame")).thenReturn(Collections.singletonList(gameTest));
        when(gameDao.findByName("GameName")).thenReturn(null);
        when(gameDao.findByName(null)).thenReturn(null);
    }

    @Test
    public void createTest() {
        Game gameTest = gameService.create("TestGame");

        Assert.assertNotNull(gameTest);
        Assert.assertEquals(gameTest.getName(), "TestGame");
    }

    @Test
    public void createTestNullName() {
        Game gameTest = gameService.create(null);

        Assert.assertNull(gameTest);
    }

    @Test
    public void removeTest() {
        gameService.remove(2L);

        Game gameTest = gameService.find(2L);
        Assert.assertNull(gameTest);
    }

    @Test
    public void findExisting() {
        Game gameTest = gameService.find(1L);

        Assert.assertEquals(gameTest, game);
    }

    @Test
    public void findNonExisting() {
        Game gameTest = gameService.find(2L);

        Assert.assertNull(gameTest);
    }

    @Test
    public void findNull() {
        Game gameTest = gameService.find(null);

        Assert.assertNull(gameTest);
    }

    @Test
    public void findAll() {
        List<Game> gameTestList = gameService.findAll();

        Assert.assertNotNull(gameTestList);
        Assert.assertEquals(gameTestList.size(), 1);
        Game gameTest = gameTestList.get(0);
        Assert.assertEquals(gameTest, game);
    }

    @Test
    public void findByName() {
        List<Game> gameTestList = gameService.findByName("TestGame");

        Assert.assertNotNull(gameTestList);
        Assert.assertEquals(gameTestList.size(), 1);
        Game gameTest = gameTestList.get(0);
        Assert.assertEquals(gameTest, game);
    }

    @Test
    public void findByNonExistingName() {
        List<Game> gameTestList = gameService.findByName("GameName");

        Assert.assertNull(gameTestList);
    }

    @Test
    public void findByNullName() {
        List<Game> gameTestList = gameService.findByName(null);

        Assert.assertNull(gameTestList);
    }
}
