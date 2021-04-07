package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Competitor;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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
public class CompetitorDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Team team;
    private Competition competition;
    private Competitor competitor;

    @Autowired
    private CompetitorDao competitorDao;

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

        em.getTransaction().begin();
        em.persist(gameTest);
        em.persist(competitionTest);
        em.persist(teamTest);
        em.getTransaction().commit();
        em.close();

        competitorDao.create(competitorTest);

        team = teamTest;
        competition = competitionTest;
        competitor = competitorTest;
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createCompetitor() {
        competitorDao.create(competitor);
    }

    @Test
    public void findCompetitor() {
        Competitor result = competitorDao.findById(competitor.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(competitor, result);
    }

    @Test
    public void findAllCompetitors() {
        List<Competitor> competitorList = competitorDao.findAll();
        Assert.assertNotNull(competitorList);
        Assert.assertTrue(competitorList.size() >= 1);
    }

    @Test
    public void updateCompetitor() {
        EntityManager em = emf.createEntityManager();

        Team teamTest = new Team();
        teamTest.setName("TeamUpdated");
        teamTest.setCreated_at(new Date());

        em.persist(teamTest);

        competitorDao.update(competitor);

        Competitor result = competitorDao.findById(competitor.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getTeam().getName(), "TeamUpdated");
        Assert.assertEquals(competitor, result);
    }

    @Test
    public void removeCompetitor() {
        competitorDao.remove(competitor);

        Competitor result = competitorDao.findById(competitor.getId());
        Assert.assertNull(result);
    }

    @Test
    public void getByCompetition() {
        List<Competitor> competitorList = competitorDao.getByCompetition(competition);
        Assert.assertNotNull(competitorList);
        Assert.assertEquals(competitorList.size(), 1);
    }

    @Test
    public void getByGame() {
        List<Competitor> competitorList = competitorDao.getByTeam(team);
        Assert.assertNotNull(competitorList);
        Assert.assertEquals(competitorList.size(), 1);
    }
}
