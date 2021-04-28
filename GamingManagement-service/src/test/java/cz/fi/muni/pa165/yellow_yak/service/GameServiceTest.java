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
    }

    @Test
    public void findExisting() {
        Game gameTest = gameService.findById(1L);

        Assert.assertEquals(gameTest, game);
    }

    @Test
    public void findNonExisting() {
        Game gameTest = gameService.findById(2L);

        Assert.assertNull(gameTest);
    }

    @Test
    public void findNull() {
        Game gameTest = gameService.findById(null);

        Assert.assertNull(gameTest);
    }

    @Test
    public void findAll() {
        List<Game> gameTestList = gameService.listAll();

        Assert.assertNotNull(gameTestList);
        Assert.assertEquals(gameTestList.size(), 1);
        Game gameTest = gameTestList.get(0);
        Assert.assertEquals(gameTest, game);
    }
}
