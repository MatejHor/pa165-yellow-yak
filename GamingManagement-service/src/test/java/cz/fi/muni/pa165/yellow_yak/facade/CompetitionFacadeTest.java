package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.dto.PlayerDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.service.BeanMappingService;
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
 * @author oreqizer, Lukas Mikula
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CompetitionFacadeTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CompetitionService competitionService;

    @Mock
    private BeanMappingService beanMappingService;

    private CompetitionFacade competitionFacade;

    private Game game;
    private Competition competition;
    private CompetitionDTO competitionDTO;

    @BeforeClass
    public void init() throws ServiceException {
        // TODO competitionService not being mocked
        MockitoAnnotations.initMocks(this);
        this.competitionFacade = new CompetitionFacadeImpl(beanMappingService, competitionService);
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

        Mockito.doReturn(competitionDTO).when(beanMappingService).mapTo(competition, CompetitionDTO.class);
        Mockito.doReturn(Collections.singletonList(competitionDTO)).when(beanMappingService).mapTo(Collections.singletonList(competition), CompetitionDTO.class);
    }

    @Test
    public void create() {
        Mockito.doReturn(competition).when(competitionService).create(game.getId(), competition.getName());

        Assert.assertEquals(competitionFacade.create(game.getId(), competition.getName()), competitionDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullGame() {
        competitionFacade.create(null, competition.getName());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullName() {
        competitionFacade.create(game.getId(), null);
    }

    @Test
    public void remove() {
        Mockito.doReturn(true).when(competitionService).remove(competition.getId());

        Assert.assertTrue(competitionService.remove(competition.getId()));
    }

    @Test
    public void removeNotExisting() {
        Mockito.doReturn(true).when(competitionService).remove(1330L);

        Assert.assertTrue(competitionService.remove(1330L));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNull() {
        competitionFacade.remove(null);
    }

    @Test
    public void findById() {
        Mockito.doReturn(competition).when(competitionService).findById(competition.getId());

        Assert.assertEquals(competitionFacade.findById(competition.getId()), competitionDTO);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNull() {
        competitionFacade.findById(null);
    }

    @Test
    public void findByGame() {
        Mockito.doReturn(Collections.singletonList(competition)).when(competitionService).findByGame(1337L);

        Assert.assertEquals(competitionFacade.findByGame(1337L), Collections.singletonList(competitionDTO));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByGameNull() {
        competitionFacade.findByGame(null);
    }

}
