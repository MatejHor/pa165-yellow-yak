package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.*;
import cz.fi.muni.pa165.yellow_yak.persistance.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author oreqizer
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class ScoreDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Competitor competitor;
    private Score score;

    @Autowired
    private ScoreDao scoreDao;

    @BeforeMethod
    private void init() {
        EntityManager em = emf.createEntityManager();

        Game gameTest = new Game();
        gameTest.setName("GameTest" + new Random().nextInt());
        gameTest.setCreated_at(new Date());

        Competition competitionTest = new Competition();
        competitionTest.setName("Competition" + new Random().nextInt());
        competitionTest.setPrices("wtf");
        competitionTest.setGame(gameTest);
        competitionTest.setCreated_at(new Date());
        competitionTest.setStart_at(new Date());

        Team teamTest = new Team();
        teamTest.setName("Team" + new Random().nextInt());
        teamTest.setCreated_at(new Date());

        Competitor competitorTest = new Competitor();
        competitorTest.setCompetition(competitionTest);
        competitorTest.setTeam(teamTest);
        competitorTest.setCreatedAt(new Date());

        Score scoreTest = new Score();
        scoreTest.setIndex(420);
        scoreTest.setCompetitor(competitorTest);
        scoreTest.setCreatedAt(new Date());

        em.getTransaction().begin();
        em.persist(gameTest);
        em.persist(competitionTest);
        em.persist(teamTest);
        em.persist(competitorTest);
        em.getTransaction().commit();
        em.close();

        scoreDao.create(scoreTest);
        score = scoreTest;
        competitor = competitorTest;
    }

    @AfterMethod
    private void remove() {
        scoreDao.remove(score);
    }

    @Test
    public void createScore() {
        Score scoreTest = new Score();
        scoreTest.setIndex(1337);
        scoreTest.setCompetitor(competitor);
        scoreTest.setCreatedAt(new Date());

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
        Assert.assertTrue(scoreList.size() >= 1);
    }

    @Test
    public void updateScore() {
        score.setIndex(1337);

        scoreDao.update(score);

        Score result = scoreDao.findById(score.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(score.getIndex(), 1337);
        Assert.assertEquals(score, result);
    }

    @Test
    public void removeScore() {
        Score scoreTest = new Score();
        scoreTest.setIndex(1337);
        scoreTest.setCompetitor(competitor);
        scoreTest.setCreatedAt(new Date());

        scoreDao.create(scoreTest);
        Assert.assertNotNull(scoreDao.findById(scoreTest.getId()));

        scoreDao.remove(scoreTest);
        Assert.assertNull(scoreDao.findById(scoreTest.getId()));
    }
}
