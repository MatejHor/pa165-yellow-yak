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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * @author Matej Knazik
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlayerDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private PlayerDao playerDao;

    private Player testPlayer;

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
        Assert.assertEquals(playerDao.findById(testPlayer2.getId()), testPlayer2);
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

    @Test
    public void removePlayerTest() {
        Assert.assertEquals(playerDao.findAll().size(), 1);
        playerDao.remove(testPlayer);
        Assert.assertEquals(playerDao.findAll().size(), 0);
    }

    @Test
    public void findAllPlayersTest() {
        Assert.assertEquals(playerDao.findAll().size(), 1);
        Assert.assertEquals(playerDao.findAll().get(0), testPlayer);
        Player testPlayer2 = new Player();
        testPlayer2.setUsername("ReadyPlayerTwo");
        testPlayer2.setCreatedAt(LocalDateTime.now());
        testPlayer2.setEmail("patchwerk@gmail.com");
        playerDao.create(testPlayer2);
        Assert.assertEquals(playerDao.findAll().size(), 2);
        List<Player> expectedPlayers = Arrays.asList(testPlayer, testPlayer2);
        Assert.assertEquals(playerDao.findAll(), expectedPlayers);
        playerDao.remove(testPlayer);
        playerDao.remove(testPlayer2);
        Assert.assertEquals(playerDao.findAll().size(), 0);
    }

    @Test
    public void findPlayerByIdTest() {
        Player receivedPlayer = playerDao.findById(testPlayer.getId());
        Assert.assertEquals(receivedPlayer, testPlayer);
        playerDao.remove(testPlayer);
        receivedPlayer = playerDao.findById(testPlayer.getId());
        Assert.assertNull(receivedPlayer);
    }

    @Test
    public void findPlayerByUsernameTest() {
        List<Player> receivedPlayers = playerDao.findByUsername(testPlayer.getUsername());
        List<Player> expectedPlayers = Arrays.asList(testPlayer);
        Assert.assertEquals(receivedPlayers, expectedPlayers);

        Player testPlayer2 = new Player();
        testPlayer2.setUsername(testPlayer.getUsername());
        testPlayer2.setCreatedAt(LocalDateTime.now());
        testPlayer2.setEmail("patchwerk@gmail.com");
        playerDao.create(testPlayer2);
        expectedPlayers = Arrays.asList(testPlayer, testPlayer2);
        receivedPlayers = playerDao.findByUsername(testPlayer.getUsername());
        Assert.assertEquals(receivedPlayers, expectedPlayers);

        playerDao.remove(testPlayer);
        playerDao.remove(testPlayer2);
        receivedPlayers = playerDao.findByUsername(testPlayer.getUsername());
        Assert.assertEquals(receivedPlayers.size(), 0);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removePlayerTestNull() {
        playerDao.remove(null);
    }

}