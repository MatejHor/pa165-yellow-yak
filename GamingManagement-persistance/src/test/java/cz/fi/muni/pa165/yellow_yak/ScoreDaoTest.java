package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.*;
import cz.fi.muni.pa165.yellow_yak.persistance.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author oreqizer, Matej Horniak
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ScoreDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Score score;
    private Competition competition;
    private Player player;
    private Game game;

    @Autowired
    private ScoreDao scoreDao;

    @BeforeMethod
    private void init() {
        EntityManager em = emf.createEntityManager();

        Game gameTest = new Game();
        gameTest.setName("GameTest" + new Random().nextInt());
        gameTest.setCreatedAt(LocalDate.now());

        Competition competitionTest = new Competition();
        competitionTest.setName("Competition" + new Random().nextInt());
        competitionTest.setPrices("wtf");
        competitionTest.setGame(gameTest);
        competitionTest.setCreatedAt(LocalDate.now());
        competitionTest.setStartedAt(LocalDate.now());

        Player playerTest = new Player();
        playerTest.setUsername("TestName");
        playerTest.setEmail("test@email.com");
        playerTest.setCreatedAt(LocalDate.now());

        Score scoreTest = new Score();
        scoreTest.setResult(420);
        scoreTest.setCreatedAt(LocalDate.now());
        scoreTest.setCompetition(competitionTest);
        scoreTest.setPlayer(playerTest);

        em.getTransaction().begin();
        em.persist(gameTest);
        em.persist(competitionTest);
        em.persist(playerTest);
        em.getTransaction().commit();
        em.close();

        scoreDao.create(scoreTest);
        competition = competitionTest;
        player = playerTest;
        score = scoreTest;
        game = gameTest;
    }

    @AfterMethod
    private void remove() {
        scoreDao.remove(score);
    }

    @Test
    public void createScore() {
        Score scoreTest = new Score();
        scoreTest.setPlacement(1337);
        scoreTest.setCreatedAt(LocalDate.now());
        scoreTest.setCompetition(competition);
        scoreTest.setPlayer(player);

        scoreDao.create(scoreTest);
    }

    @Test
    public void findScore() {
        Score result = scoreDao.findById(score.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(score, result);
    }

    @Test
    public void findAllScores() {
        List<Score> scoreList = scoreDao.findAll();
        Assert.assertNotNull(scoreList);
        Assert.assertEquals(scoreList.size(), 1);
    }

    @Test
    public void updateScore() {
        score.setPlacement(1337);

        scoreDao.update(score);

        Score result = scoreDao.findById(score.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(score.getPlacement(), 1337);
        Assert.assertEquals(score, result);
    }

    @Test
    public void removeScore() {
        Score scoreTest = new Score();
        scoreTest.setPlacement(1337);
        scoreTest.setCreatedAt(LocalDate.now());
        scoreTest.setCompetition(competition);
        scoreTest.setPlayer(player);

        scoreDao.create(scoreTest);
        Assert.assertNotNull(scoreDao.findById(scoreTest.getId()));

        scoreDao.remove(scoreTest);
        Assert.assertNull(scoreDao.findById(scoreTest.getId()));
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeScoreTestNull() {
        scoreDao.remove(null);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateScoreTest() {
        List<Competition> competitions = new ArrayList<>();
        competitions.add(competition);
        List<Score> result = scoreDao.findByPlayerAndCompetitionAndDate(
                player.getId(),
                competitions,
                score.getCreatedAt());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Score resultScore = result.get(0);
        Assert.assertEquals(resultScore, score);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateScoreTestNotPlayer() {
        List<Competition> competitions = new ArrayList<>();
        competitions.add(competition);
        List<Score> result = scoreDao.findByPlayerAndCompetitionAndDate(
                null,
                competitions,
                score.getCreatedAt());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateScoreTestNotCompetition() {
        List<Score> result = scoreDao.findByPlayerAndCompetitionAndDate(
                player.getId(),
                null,
                score.getCreatedAt());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateScoreTestNotCreatedAt() {
        List<Competition> competitions = new ArrayList<>();
        competitions.add(competition);
        List<Score> result = scoreDao.findByPlayerAndCompetitionAndDate(
                player.getId(),
                competitions,
                null);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateScoreTestEveryParamNull() {
        List<Score> result = scoreDao.findByPlayerAndCompetitionAndDate(
                null,
                null,
                null);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByPlayerAndGameTest() {
        List<Score> result = scoreDao.findByPlayerAndGame(
                player.getId(),
                game.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Score resultScore = result.get(0);
        Assert.assertEquals(resultScore, score);
    }

    @Test
    public void findByPlayerAndGameTestNotPlayer() {
        List<Score> result = scoreDao.findByPlayerAndGame(
               null,
                game.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByPlayerAndGameTestNotGame() {
        List<Score> result = scoreDao.findByPlayerAndGame(
                player.getId(),
                null);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByPlayerAndGameTestNotParam() {
        List<Score> result = scoreDao.findByPlayerAndGame(
                null,
               null);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findByCompetitionTest() {
        List<Score> result = scoreDao.findByCompetition(
                competition.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Score resultScore = result.get(0);
        Assert.assertEquals(resultScore, score);
    }

    @Test
    public void findByCompetitionTestNotCompetition() {
        List<Score> result = scoreDao.findByCompetition(
                null);

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 0);
    }

    @Test
    public void findCompetitionResultTest() {
        EntityManager em = emf.createEntityManager();

        Player player = new Player();
        player.setUsername("Second");
        player.setEmail("test@email.com");
        player.setCreatedAt(LocalDate.now());

        Score score2 = new Score();
        score2.setResult(419);
        score2.setCreatedAt(LocalDate.now());
        score2.setCompetition(competition);
        score2.setPlayer(player);

        em.getTransaction().begin();
        em.persist(player);
        em.persist(score2);
        em.getTransaction().commit();

        List<Score> result = scoreDao.findCompetitionResults(competition.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 2);
        Assert.assertEquals(result.get(0), score);
        Assert.assertEquals(result.get(1), score2);
    }

    @Test
    public void findCompetitionResultZeroTest() {
        EntityManager em = emf.createEntityManager();

        Player player = new Player();
        player.setUsername("Second");
        player.setEmail("test@email.com");
        player.setCreatedAt(LocalDate.now());

        Score score2 = new Score();
        score2.setCreatedAt(LocalDate.now());
        score2.setCompetition(competition);
        score2.setPlayer(player);

        em.getTransaction().begin();
        em.persist(player);
        em.persist(score2);
        em.getTransaction().commit();

        List<Score> result = scoreDao.findCompetitionResults(competition.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.size(), 1);
        Assert.assertEquals(result.get(0), score);
    }

}
