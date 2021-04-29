package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.service.CompetitionService;
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

import java.util.Collections;

/**
 * @author oreqizer
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CompetitionFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CompetitionService competitionService;

    @Autowired
    @InjectMocks
    private CompetitionFacade competitionFacade;

    private Game game;
    private Competition competition;
    private CompetitionDTO competitionDTO;

    @BeforeClass
    public void init() throws ServiceException {
        // TODO competitionService not being mocked
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void setup() {
        game = new Game();
        game.setId(1337L);
        game.setName("Battlefield 6");

        competition = new Competition();
        competition.setId(1338L);
        competition.setName("zergfest");

        GameDTO gameDTO = new GameDTO();
        gameDTO.setId(game.getId());
        gameDTO.setName(game.getName());

        competitionDTO = new CompetitionDTO();
        competitionDTO.setId(competition.getId());
        competitionDTO.setGame(gameDTO);
        competitionDTO.setName(competition.getName());
    }

    @Test
    public void create() {
//        Mockito.doReturn(competition).when(competitionService).create(game.getId(), competition.getName());
//
//        Assert.assertEquals(competitionFacade.create(game.getId(), competition.getName()), competitionDTO);
    }

    @Test
    public void remove() {
//        competitionFacade.remove(1337L);
    }

    @Test
    public void findById() {
//        Mockito.doReturn(competition).when(competitionService).findById(competition.getId());
//
//        Assert.assertEquals(competitionFacade.findById(competition.getId()), competitionDTO);
    }

    @Test
    public void findByGame() {
//        Mockito.doReturn(competition).when(competitionService).findByGame(1337L);
//
//        Assert.assertEquals(competitionFacade.findByGame(1337L), Collections.singletonList(competitionDTO));
    }

}
