package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;

/**
 * @author Lukas Mikula
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class GameDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;
    private Game game;
    @Autowired
    private GameDao gameDao;

    @BeforeMethod
    private void init() {
        Game gameTest = new Game();
        gameTest.setName("TestGame");
        gameTest.setCreated_at(new Date());

        gameDao.create(gameTest);
        game = gameTest;
    }

    @AfterMethod
    private void remove() {
        gameDao.remove(game);
    }

    @Test
    public void createGame() {
        Game gameTest = new Game();
        gameTest.setName("NewGame");
        gameTest.setCreated_at(new Date());

        gameDao.create(gameTest);

        Assert.assertEquals(gameDao.findById(gameTest.getId()).getName(),"NewGame");
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createGameNullName() {
        Game gameTest = new Game();
        gameTest.setName(null);
        gameTest.setCreated_at(new Date());

        gameDao.create(gameTest);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createGameDuplicateName() {
        Game gameTest = new Game();
        gameTest.setName("TestGame");
        gameTest.setCreated_at(new Date());

        gameDao.create(gameTest);
    }

    @Test
    public void findByNameGame() {
        Game gameTest = gameDao.findByName(game.getName());

        Assert.assertNotNull(gameTest);
        Assert.assertEquals(gameTest.getName(), game.getName());
    }

    @Test
    public void findGame() {
        Game result = gameDao.findById(game.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(game, result);
        Assert.assertEquals(game.getName(), result.getName());
    }

    @Test
    public void findGameNonExistingId() {
        Game result = gameDao.findById(1000L);

        Assert.assertNull(result);
    }

    @Test
    public void findAllCompetition() {
        List<Game> gameList = gameDao.findAll();

        Assert.assertNotNull(gameList);
        Assert.assertTrue(gameList.size() >= 1);
    }

    @Test
    public void updateGame() {
        String oldName = game.getName();
        String newName = "TestUpdateNew";
        game.setName(newName);

        gameDao.update(game);

        Game result = gameDao.findById(game.getId());

        Assert.assertNotNull(result);
        Assert.assertNotEquals(game.getName(), oldName);
        Assert.assertEquals(game, result);
        Assert.assertEquals(result.getName(), newName);
    }

    @Test
    public void removeGame() {
        Game gameTest  = new Game();
        gameTest.setName("GameForRemove");
        gameTest.setCreated_at(new Date());

        gameDao.create(gameTest);

        Assert.assertNotNull(gameDao.findById(gameTest.getId()));

        gameDao.remove(gameTest);

        Assert.assertNull(gameDao.findById(gameTest.getId()));
    }

}
