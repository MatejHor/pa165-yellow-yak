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
import cz.fi.muni.pa165.yellow_yak.service.ScoreService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

/**
 * @author Matej Horniak, oreqizer
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ScoreFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private ScoreService scoreService;

    @Autowired
    @InjectMocks
    private ScoreFacade scoreFacade;

    private Game game;
    private Competition competition;
    private Player player;
    private Score score;
    private ScoreDTO scoreDTO;

    @BeforeClass
    public void init() throws ServiceException {
        // TODO scoreService not being mocked
        MockitoAnnotations.initMocks(this);
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
    }

    @Test
    public void create() {
//        Mockito.doReturn(score).when(scoreService).create(competition.getId(), player.getId());
//
//        Assert.assertEquals(scoreFacade.create(competition.getId(), player.getId()), scoreDTO);
    }

    @Test
    public void remove() {
//        scoreFacade.remove(1337L);
    }

    @Test
    public void findById() {
//        Mockito.doReturn(score).when(scoreService).findById(score.getId());
//
//        Assert.assertEquals(scoreFacade.findById(score.getId()), scoreDTO);
    }

    @Test
    public void setResult() {
//        int result = 420;
//        Mockito.doReturn(score).when(scoreService).setResult(score.getId(), result);
//
//        Assert.assertEquals(scoreFacade.setResult(score.getId(), result), scoreDTO);
    }

    @Test
    public void findByPlayerGame() {
//        Mockito.doReturn(Collections.singletonList(score)).when(scoreService).findByPlayerAndGame(player.getId(), game.getId());
//
//        Assert.assertEquals(scoreFacade.findByPlayerGame(player.getId(), game.getId()), Collections.singletonList(scoreDTO));
    }

    @Test
    public void findByCompetition() {
//        Mockito.doReturn(Collections.singletonList(score)).when(scoreService).findByCompetition(competition.getId());
//
//        Assert.assertEquals(scoreFacade.findByCompetition(competition.getId()), Collections.singletonList(scoreDTO));
    }

}
