package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import java.time.LocalDateTime;

/**
 * @author Matej Knazik
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlayerDaoTest extends AbstractTestNGSpringContextTests {
    @PersistenceUnit
    private Player testPlayer;

    @Autowired
    private PlayerDao playerDao;

    @BeforeMethod
    private void beforeEach() {
        Player testPlayer1 = new Player();

        testPlayer1.setUsername("ReadyPlayerOne");
        testPlayer1.setCreatedAt(LocalDateTime.now());
        testPlayer1.setEmail("festergut@gmail.com");


        playerDao.create(testPlayer1);
        testPlayer = testPlayer1;
    }

    @AfterMethod
    private void afterEach() {
        playerDao.findAll().forEach(player -> playerDao.remove(player));
    }

    @Test
    public void createPlayerTest() {
        Assert.assertEquals(playerDao.findAll().size(), 1);
        Player testPlayer2 = new Player();
        testPlayer2.setUsername("ReadyPlayerTwo");
        testPlayer2.setCreatedAt(LocalDateTime.now());
        testPlayer2.setEmail("patchwerk@gmail.com");
        playerDao.create(testPlayer2);
        Assert.assertEquals(playerDao.findAll().size(), 2);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createPlayerTestDuplicate() {
        Assert.assertEquals(playerDao.findAll().size(), 1);
        playerDao.create(testPlayer);
    }

    @Test
    public void updatePlayerTest() {
        Assert.assertEquals(playerDao.findAll().size(), 1);
        String usernameBefore = testPlayer.getUsername();
        String emailBefore = testPlayer.getEmail();
        Player pBefore = playerDao.findById(testPlayer.getId());
        Assert.assertEquals(pBefore.getUsername(),usernameBefore);
        Assert.assertEquals(pBefore.getEmail(),emailBefore);

        String usernameAfter = "traitor";
        String emailAfter = "rotface@gmail.com";

        testPlayer.setUsername(usernameAfter);
        testPlayer.setEmail(emailAfter);

        playerDao.update(testPlayer);
        Assert.assertEquals(playerDao.findAll().size(), 1);
        Player pAfter = playerDao.findById(testPlayer.getId());
        Assert.assertEquals(pAfter.getUsername(),usernameAfter);
        Assert.assertEquals(pAfter.getEmail(),emailAfter);
        Assert.assertEquals(testPlayer, pAfter);
    }
}
