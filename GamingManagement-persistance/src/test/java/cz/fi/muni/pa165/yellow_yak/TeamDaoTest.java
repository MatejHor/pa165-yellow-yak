package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.TeamDao;
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
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Matej Knazik
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TeamDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;
    private Team testTeam;

    @Autowired
    private TeamDao teamDao;

    @BeforeMethod
    private void beforeEach() {
        EntityManager em = entityManagerFactory.createEntityManager();

        Team testTeam1 = new Team();
        testTeam1.setCreatedAt(LocalDateTime.now());
        testTeam1.setName("Test Team" + new Random().nextInt());
        Team testTeam2 = new Team();
        testTeam2.setCreatedAt(LocalDateTime.now().minusDays( 1 ));
        testTeam2.setName("Test Team" + + new Random().nextInt());

        teamDao.create(testTeam1);
        teamDao.create(testTeam2);
        testTeam = testTeam1;
    }

    @AfterMethod
    private void remove() {
        teamDao.remove(testTeam);
    }


    @Test(expectedExceptions = PersistenceException.class)
    public void createTeamTest() {
        Assert.assertTrue(teamDao.getAll().size()>0);
        teamDao.create(testTeam);
    }

    @Test
    public void updateTeamTest() {
        String nameBefore = testTeam.getName();
        String nameAfter = "Awesome team";

        Assert.assertEquals(teamDao.getById(testTeam.getId()).getName(), nameBefore);
        testTeam.setName(nameAfter);
        teamDao.update(testTeam);

        Team changedTestTeam = teamDao.getById(testTeam.getId());
        Assert.assertNotNull(changedTestTeam);
        Assert.assertEquals(changedTestTeam.getName(), nameAfter);
        Assert.assertEquals(testTeam, changedTestTeam);
    }

    @Test
    public void removeTeamTest() {
        Team team = new Team();
        team.setCreatedAt(LocalDateTime.now());
        team.setName("TestTeamRemove");
        teamDao.create(team);

        int teamsCountBefore = teamDao.getAll().size();
        teamDao.remove(team);
        Assert.assertEquals(teamsCountBefore - 1,teamDao.getAll().size());

        Team removedTestTeam = teamDao.getById(team.getId());
        Assert.assertNull(removedTestTeam);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeTeamTestNull() {
        teamDao.remove(null);
    }

    @Test
    public void getAllTeamsTest() {
        List<Team> teamList = teamDao.getAll();
        Assert.assertNotNull(teamList);
        Assert.assertTrue(teamList.size() >= 2);

    }

    @Test
    public void getTeamByIdTest() {
        Team desiredTeam = teamDao.getById(testTeam.getId());
        Assert.assertNotNull(desiredTeam);
        Assert.assertEquals(testTeam, desiredTeam);
    }

    @Test
    public void getTeamByNameTest() {
        List<Team> teamList = teamDao.getByName(testTeam.getName());
        Assert.assertNotNull(teamList);
        Assert.assertEquals(teamList.size(), 1);

        Assert.assertEquals(teamList.get(0), testTeam);
    }

    // TODO tests

}
