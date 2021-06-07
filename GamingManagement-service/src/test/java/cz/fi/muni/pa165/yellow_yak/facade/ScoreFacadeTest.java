package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
import cz.fi.muni.pa165.yellow_yak.service.CompetitionService;
import cz.fi.muni.pa165.yellow_yak.service.ScoreService;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Tests for score facade
 *
 * @author Matej Horniak, oreqizer, Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ScoreFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ScoreService scoreService;

    @Mock
    private BeanMappingService beanMappingService;

    @Mock
    private CompetitionService competitionService;

    @Autowired
    @InjectMocks
    private ScoreFacade scoreFacade;

    private Game game;
    private Competition competition;
    private Player player;
    private Score score;
    private ScoreDTO scoreDTO;
    private List<Competition> competitions;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
        this.scoreFacade = new ScoreFacadeImpl(beanMappingService, competitionService, scoreService);
    }

    @BeforeMethod
    public void setup() {
        game = new Game();
        game.setId(420L);
        game.setName("Battlefield 6");

        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setName(game.getName());

        competition = new Competition();
        competition.setId(9001L);
        competition.setName("zergfest");
        competition.setGame(game);

        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setId(competition.getId());
        competitionDTO.setName(competition.getName());
        competitionDTO.setGame(gameDTO);

        competitions = new ArrayList<>();
        competitions.add(competition);

        player = new Player();
        player.setId(1337L);
        player.setUsername("xxx_BILLY_xxx");
        player.setEmail("lol@kek.bur");

        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setUsername(player.getUsername());
        playerDTO.setEmail(player.getEmail());

        score = new Score();
        score.setId(1338L);
        score.setCompetition(competition);
        score.setPlayer(player);

        scoreDTO = new ScoreDTO();
        scoreDTO.setId(score.getId());
        scoreDTO.setCompetition(competitionDTO);
        scoreDTO.setPlayer(playerDTO);

        Mockito.doReturn(scoreDTO).when(beanMappingService).mapTo(score, ScoreDTO.class);
        Mockito.doReturn(Collections.singletonList(scoreDTO)).when(beanMappingService).mapTo(Collections.singletonList(score), ScoreDTO.class);
    }

    @Test
    public void create() {
        Mockito.doReturn(score).when(scoreService).create(competition.getId(), player.getId());

        Assert.assertEquals(scoreFacade.create(competition.getId(), player.getId()), scoreDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullPlayerId() {
        scoreFacade.create(1L, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullCompetitionId() {
        scoreFacade.create(null, 1L);
    }

    @Test
    public void remove() {
        Mockito.doReturn(true).when(scoreService).remove(score.getId());
        Assert.assertTrue(scoreFacade.remove(1338L));
    }

    @Test
    public void removeNotExists() {
        Mockito.doReturn(true).when(scoreService).remove(1337L);
        Assert.assertTrue(scoreFacade.remove(1337L));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullId() {
        scoreFacade.remove(null);
    }

    @Test
    public void findById() {
        Mockito.doReturn(score).when(scoreService).findById(score.getId());

        Assert.assertEquals(scoreFacade.findById(score.getId()), scoreDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullId() {
        scoreFacade.findById(null);
    }

    @Test
    public void setResult() {
        int result = 420;
        Mockito.doReturn(score).when(scoreService).setResult(score.getId(), result);

        Assert.assertEquals(scoreFacade.setResult(score.getId(), result), scoreDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setResultNullId() {
        scoreFacade.setResult(null, 1);
    }

    @Test
    public void findByPlayerGame() {
        Mockito.doReturn(Collections.singletonList(score)).when(scoreService).findByPlayerAndGame(player.getId(), game.getId());

        Assert.assertEquals(scoreFacade.findByPlayerGame(player.getId(), game.getId()), Collections.singletonList(scoreDTO));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByPlayerGameNullGameId() {
        scoreFacade.findByPlayerGame(1L, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByPlayerGameNullPlayerId() {
        scoreFacade.findByPlayerGame(null, 1L);
    }

    @Test
    public void findByCompetition() {
        Mockito.doReturn(Collections.singletonList(score)).when(scoreService).findByCompetition(competition.getId());

        Assert.assertEquals(scoreFacade.findByCompetition(competition.getId()), Collections.singletonList(scoreDTO));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByCompetitionNullCompetitionId() {
        scoreFacade.findByCompetition(null);
    }

    @Test
    public void findByGamePlayerDate() {
        Mockito.doReturn(Collections.singletonList(score)).when(scoreService).findByPlayerAndCompetitionAndDate(
                player.getId(),
                competitions,
                LocalDate.now());
        Mockito.doReturn(competitions).when(competitionService).findByGame(game.getId());

        Assert.assertEquals(scoreFacade.findByGamePlayerDate(
                player.getId(),
                game.getId(),
                LocalDate.now())
                , Collections.singletonList(scoreDTO));

    }
}
