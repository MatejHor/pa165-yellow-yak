package cz.fi.muni.pa165.yellow_yak.service;


import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
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

import static org.mockito.Mockito.when;

/**
 * @author matho
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class CompetitionServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private CompetitionDao competitionDao;

    @Autowired
    @InjectMocks
    private CompetitionService competitionService;

    private Competition competition;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @BeforeMethod
    public void createCompetitions() {
        Game game = new Game();
        game.setName("TestGame");

        Competition competitionTest = new Competition();
        competitionTest.setPrices("TestPrices");
        competitionTest.setName("TestNameCompetition");
        competitionTest.setCreatedAt(LocalDateTime.now());
        competitionTest.setId(1L);
        competitionTest.setStartedAt(LocalDateTime.now());
        competitionTest.setGame(game);

        competition = competitionTest;

        when(competitionDao.findByGame(1L)).thenReturn(Collections.singletonList(competitionTest));
        when(competitionDao.findByGame(null)).thenReturn(Collections.emptyList());
        when(competitionDao.findByGame(2L)).thenReturn(Collections.emptyList());
        when(competitionDao.findOldest()).thenReturn(competitionTest);
    }

    @Test
    public void findByGameTest() {
        List<Competition> competitionList = competitionService.listByGame(1L);

        Assert.assertNotNull(competitionList);
        Assert.assertEquals(competitionList.size(), 1);
        Competition result = competitionList.get(0);
        Assert.assertEquals(result, competition);
    }

    @Test
    public void findByGameTestNullId() {
        List<Competition> competitionList = competitionService.listByGame(null);

        Assert.assertNotNull(competitionList);
        Assert.assertEquals(competitionList.size(), 0);
    }

    @Test
    public void findByGameTestNotExistId() {
        List<Competition> competitionList = competitionService.listByGame(2L);

        Assert.assertNotNull(competitionList);
        Assert.assertEquals(competitionList.size(), 0);
    }

}
