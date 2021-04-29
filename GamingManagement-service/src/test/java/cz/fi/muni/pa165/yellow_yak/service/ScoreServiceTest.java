package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import cz.fi.muni.pa165.yellow_yak.persistance.ScoreDao;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

/**
 * @author Lukas Mikula, Matej Horniak
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ScoreServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ScoreDao scoreDao;

    @Mock
    private PlayerDao playerDao;

    @Mock
    private CompetitionDao competitionDao;

    @Autowired
    @InjectMocks
    private ScoreService scoreService;

    @Autowired
    @InjectMocks
    private PlayerService playerService;

    @Autowired
    @InjectMocks
    private CompetitionService competitionService;

    private Score score;
    private List<Competition> competitions;
    private LocalDateTime created;
    private Competition competition;
    private Player player;
    private Game game;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createScore() {
        Game gameTest = new Game();
        gameTest.setName("TestGame");

        Competition competitionTest = new Competition();
        competitionTest.setPrices("TestPrices");
        competitionTest.setName("TestNameCompetition");
        competitionTest.setCreatedAt(LocalDateTime.now());
        competitionTest.setId(1L);
        competitionTest.setStartedAt(LocalDateTime.now());
        competitionTest.setGame(gameTest);

        competitions = new ArrayList<>();
        competitions.add(competitionTest);

        Player playerTest = new Player();
        playerTest.setUsername("TestPlayerName");
        playerTest.setEmail("TestPlayerEmail");

        Score scoreTest = new Score();
        scoreTest.setCompetition(competitionTest);
        scoreTest.setPlayer(playerTest);
        created = LocalDateTime.of(2021, 4, 25, 12, 00);
        scoreTest.setCreatedAt(created);

        score = scoreTest;
        competition = competitionTest;
        player = playerTest;
        game = gameTest;

        when(scoreDao.findByPlayerAndCompetitionAndDate(1L, competitions, created)).thenReturn(Collections.singletonList(scoreTest));
        when(scoreDao.findByPlayerAndCompetitionAndDate(null, competitions, created)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(1L, null, created)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(1L, competitions, null)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(null, null, null)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(2L, competitions, LocalDateTime.now())).thenReturn(Collections.emptyList());

        when(scoreDao.findByCompetition(1L)).thenReturn(Collections.singletonList(score));
        when(scoreDao.findByCompetition(null)).thenReturn(Collections.emptyList());
        when(scoreDao.findByCompetition(111L)).thenReturn(Collections.emptyList());

        when(scoreDao.findByPlayerAndGame(1L, 1L)).thenReturn(Collections.singletonList(score));
        when(scoreDao.findByPlayerAndGame(null,null)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndGame(1L,null)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndGame(null, 1L)).thenReturn(Collections.emptyList());

        when(scoreDao.findById(1L)).thenReturn(score);
        when(scoreDao.findById(2L)).thenReturn(null);
        when(scoreDao.findById(null)).thenReturn(null);

        when(playerDao.findById(null)).thenReturn(null);
        when(competitionDao.findById(null)).thenReturn(null);
        when(playerDao.findById(1L)).thenReturn(playerTest);
        when(competitionDao.findById(1L)).thenReturn(competitionTest);
    }

    @Test
    public void findByPlayerAndCompetitionAndDate() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(1L, competitions, created);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 1);
        Score scoreTest = scoreTestList.get(0);
        Assert.assertEquals(scoreTest, score);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateNullPlayer() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(null, competitions, created);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateNullCompetition() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(1L, null, created);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateNullDate() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(1L, competitions, null);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateNullAll() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(null, null, null);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateNonExisting() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(2L, competitions, LocalDateTime.now());

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByCompetitionTest() {
        List<Score> scoreTestList = scoreService.findByCompetition(1L);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 1);
        Score scoreTest = scoreTestList.get(0);
        Assert.assertEquals(scoreTest, score);
    }

    @Test
    public void findByCompetitionTestNullId() {
        List<Score> scoreTestList = scoreService.findByCompetition(null);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByCompetitionTestNotFoundId() {
        List<Score> scoreTestList = scoreService.findByCompetition(111L);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByPlayerAndGameTest() {
        List<Score> scoreTestList = scoreService.findByPlayerAndGame(1L, 1L);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 1);
        Score scoreTest = scoreTestList.get(0);
        Assert.assertEquals(scoreTest, score);
    }

    @Test
    public void findByPlayerAndGameTestNotPlayer() {
        List<Score> scoreTestList = scoreService.findByPlayerAndGame(null, 1L);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByPlayerAndGameTestNotGame() {
        List<Score> scoreTestList = scoreService.findByPlayerAndGame(1L, null);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByPlayerAndGameTestNotParams() {
        List<Score> scoreTestList = scoreService.findByPlayerAndGame(null, null);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }

    @Test
    public void findByIdTest() {
        Score scoreTest = scoreService.findById(1L);

        Assert.assertNotNull(scoreTest);
        Assert.assertEquals(scoreTest, score);
    }

    @Test
    public void findByIdTestNullId() {
        Score scoreTest = scoreService.findById(null);

        Assert.assertNull(scoreTest);
    }

    @Test
    public void findByIdTestNotExistId() {
        Score scoreTest = scoreService.findById(2L);

        Assert.assertNull(scoreTest);
    }

    @Test
    public void removeTest() {
        when(scoreDao.findById(4L)).thenReturn(null);
        scoreService.remove(4L);

        Score scoreTest = scoreService.findById(4L);
        Assert.assertNull(scoreTest);
    }

    @Test
    public void createTest() {
        Score scoreTest = scoreService.create(1L, 1L);

        Assert.assertNotNull(scoreTest);
        Assert.assertEquals(scoreTest.getPlayer(), player);
        Assert.assertEquals(scoreTest.getCompetition(), competition);
    }

    @Test
    public void createTestNotCompetitionId() {
        Score scoreTest = scoreService.create(null, 1L);

        Assert.assertNull(scoreTest);
    }

    @Test
    public void createTestNotPlayerId() {
        Score scoreTest = scoreService.create(1L, null);

        Assert.assertNull(scoreTest);
    }

    @Test
    public void createTestNotCompetitionIdAndPlayerId() {
        Score scoreTest = scoreService.create(null, null);

        Assert.assertNull(scoreTest);
    }
}
