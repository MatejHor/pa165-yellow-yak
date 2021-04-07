package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
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
 * @author Matej Horniak
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
public class CompetitionDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;
    private Competition competition;

    @Autowired
    private CompetitionDao competitionDao;

    @BeforeMethod
    private void init() {
        EntityManager em = emf.createEntityManager();

        Game gameTest = new Game();
        gameTest.setName("GameTest" + new Random().nextInt());
        gameTest.setCreated_at(new Date());

        Competition competitionTest = new Competition();
        competitionTest.setName("Test" + new Random().nextInt());
        competitionTest.setPrices("Price1, Price2");
        competitionTest.setCreated_at(new Date());
        competitionTest.setFinish_at(new Date());
        competitionTest.setStart_at(new Date());
        competitionTest.setGame(gameTest);

        em.getTransaction().begin();
        em.persist(gameTest);
        em.getTransaction().commit();
        em.close();

        competitionDao.create(competitionTest);
        competition = competitionTest;
    }

    @AfterMethod
    private void remove() {
        if (competitionDao.findById(competition.getId()) != null) {
            competitionDao.remove(competition);
        }
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createCompetition() {
        competitionDao.create(competition);
    }

    @Test
    public void findByNameCompetition() {
        List<Competition> competitionList = competitionDao.findByName(competition.getName());
        Assert.assertNotNull(competitionList);
        Assert.assertEquals(competitionList.size(), 1);

        Competition result = competitionList.get(0);
        Assert.assertEquals(competition, result);
    }

    @Test
    public void findCompetition() {
        Competition result = competitionDao.findById(competition.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(competition, result);
        Assert.assertNull(competitionDao.findById(123124L));
    }

    @Test
    public void findAllCompetition() {
        List<Competition> competitionList = competitionDao.findAll();
        Assert.assertNotNull(competitionList);
        Assert.assertTrue(competitionList.size() >= 1);
    }

    @Test
    public void updateCompetition() {
        String oldName = competition.getName();
        String newName = "TestUpdateNew";
        competition.setName(newName);

        competitionDao.update(competition);

        Competition result = competitionDao.findById(competition.getId());
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getName(), newName);
        Assert.assertNotEquals(competition.getName(), oldName);
        Assert.assertEquals(competition, result);
    }


    @Test
    public void removeCompetition() {
        competitionDao.remove(competition);

        Competition result = competitionDao.findById(competition.getId());
        Assert.assertNull(result);
        List<Competition> resultList = competitionDao.findByName(competition.getName());
        Assert.assertNotNull(resultList);
        Assert.assertEquals(resultList.size(), 0);
    }
}
