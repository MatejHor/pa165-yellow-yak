package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.persistance.ScoreDao;
import net.bytebuddy.asm.Advice;
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
import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ScoreServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ScoreDao scoreDao;

    @Autowired
    @InjectMocks
    private ScoreService scoreService;

    private Score score;

    private LocalDateTime created;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createScore() {
        Game game = new Game();
        game.setName("TestGame");

        Competition competitionTest = new Competition();
        competitionTest.setPrices("TestPrices");
        competitionTest.setName("TestNameCompetition");
        competitionTest.setCreatedAt(LocalDateTime.now());
        competitionTest.setId(1L);
        competitionTest.setStartedAt(LocalDateTime.now());
        competitionTest.setGame(game);

        Player playerTest = new Player();
        playerTest.setUsername("TestPlayerName");
        playerTest.setEmail("TestPlayerEmail");

        Score scoreTest = new Score();
        scoreTest.setCompetition(competitionTest);
        scoreTest.setPlayer(playerTest);
        created = LocalDateTime.of(2021, 4, 25, 12, 00);
        scoreTest.setCreatedAt(created);

        score = scoreTest;

        when(scoreDao.findByPlayerAndCompetitionAndDate(1L, 1L, created)).thenReturn(Collections.singletonList(scoreTest));
        when(scoreDao.findByPlayerAndCompetitionAndDate(null, 1L, created)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(1L, null, created)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(1L, 1L, null)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(null, null, null)).thenReturn(Collections.emptyList());
        when(scoreDao.findByPlayerAndCompetitionAndDate(2L, 2L, LocalDateTime.now())).thenReturn(Collections.emptyList());
    }

    @Test
    public void findByPlayerAndCompetitionAndDate() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(1L, 1L, created);

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 1);
        Score scoreTest = scoreTestList.get(0);
        Assert.assertEquals(scoreTest, score);
    }

    @Test
    public void findByPlayerAndCompetitionAndDateNullPlayer() {
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(null, 1L, created);

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
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(1L, 1L, null);

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
        List<Score> scoreTestList = scoreService.findByPlayerAndCompetitionAndDate(2L, 2L, LocalDateTime.now());

        Assert.assertNotNull(scoreTestList);
        Assert.assertEquals(scoreTestList.size(), 0);
    }
}
