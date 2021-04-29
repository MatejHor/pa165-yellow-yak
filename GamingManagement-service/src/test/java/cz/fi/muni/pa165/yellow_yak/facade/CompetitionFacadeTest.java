package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.dto.CompetitionDTO;
import cz.fi.muni.pa165.yellow_yak.dto.GameDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
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
import org.testng.annotations.Test;

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

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create() {
//        Game game = new Game();
//        game.setId(1337L);
//        game.setName("Battlefield 6");
//
//        Competition competition = new Competition();
//        competition.setId(1338L);
//        competition.setName("zergfest");
//
//        Mockito.doReturn(competition).when(competitionService).create(game.getId(), competition.getName());
//
//        GameDTO wantGame = new GameDTO();
//        wantGame.setId(game.getId());
//        wantGame.setName(game.getName());
//
//        CompetitionDTO want = new CompetitionDTO();
//        want.setId(competition.getId());
//        want.setGame(wantGame);
//        want.setName(competition.getName());
//
//        Assert.assertEquals(competitionFacade.create(game.getId(), competition.getName()), want);
    }

}
