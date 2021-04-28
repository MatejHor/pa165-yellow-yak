package cz.fi.muni.pa165.yellow_yak;

import cz.fi.muni.pa165.yellow_yak.entity.Member;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import cz.fi.muni.pa165.yellow_yak.persistance.MemberDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceException;
import javax.persistence.PersistenceUnit;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author oreqizer
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    private EntityManagerFactory emf;

    private Player testPlayer;
    private Team testTeam;
    private Member testMember;

    @Autowired
    private MemberDao memberDao;

    @BeforeMethod
    private void beforeEach() {
        EntityManager em = emf.createEntityManager();

        testPlayer = new Player();
        testPlayer.setUsername("Player1");
        testPlayer.setEmail("kek@lol.bur");
        testPlayer.setCreatedAt(LocalDateTime.now());

        testTeam = new Team();
        testTeam.setName("Team1");
        testTeam.setCreatedAt(LocalDateTime.now());

        testMember = new Member();
        testMember.setPlayer(testPlayer);
        testMember.setTeam(testTeam);
        testMember.setCreatedAt(LocalDate.now());

        em.getTransaction().begin();
        em.persist(testPlayer);
        em.persist(testTeam);
        em.persist(testMember);
        em.getTransaction().commit();
        em.close();
    }

    @Test
    public void createMemberTest() {
        EntityManager em = emf.createEntityManager();

        Player player = new Player();
        player.setUsername("Player2");
        player.setEmail("lol@kek.lmao");
        player.setCreatedAt(LocalDateTime.now());

        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();

        Member member = new Member();
        member.setPlayer(player);
        member.setTeam(testTeam);
        member.setCreatedAt(LocalDate.now());

        member.setPlayer(player);
        memberDao.create(member);

        Assert.assertEquals(member.getPlayer(), player);
    }

    @Test(expectedExceptions = PersistenceException.class)
    public void createMemberDuplicateTest() {
        memberDao.create(testMember);
    }

    @Test
    public void updateMemberTest() {
        EntityManager em = emf.createEntityManager();

        Player player = new Player();
        player.setUsername("Player2");
        player.setEmail("lol@kek.lmao");
        player.setCreatedAt(LocalDateTime.now());

        em.getTransaction().begin();
        em.persist(player);
        em.getTransaction().commit();
        em.close();

        Assert.assertEquals(testMember.getPlayer(), testPlayer);

        testMember.setPlayer(player);
        memberDao.update(testMember);

        Assert.assertEquals(testMember.getPlayer(), player);
    }

    @Test
    public void removeMemberTest() {
        Assert.assertNotNull(memberDao.findById(testMember.getId()));

        memberDao.remove(testMember);

        Assert.assertNull(memberDao.findById(testMember.getId()));
    }

    @Test
    public void getAllMembersTest() {
        List<Member> memberList = memberDao.findAll();

        Assert.assertNotNull(memberList);
        Assert.assertEquals(memberList.size(), 1);

    }

    @Test
    public void getMemberByIdTest() {
        Member member = memberDao.findById(testMember.getId());

        Assert.assertNotNull(member);
        Assert.assertEquals(testMember, member);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void removeMemberTestNull() {
        memberDao.remove(null);
    }
    
}
