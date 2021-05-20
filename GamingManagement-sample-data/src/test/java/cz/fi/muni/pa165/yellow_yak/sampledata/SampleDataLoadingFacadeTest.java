package cz.fi.muni.pa165.yellow_yak.sampledata;

import cz.fi.muni.pa165.yellow_yak.persistance.CompetitionDao;
import cz.fi.muni.pa165.yellow_yak.persistance.GameDao;
import cz.fi.muni.pa165.yellow_yak.persistance.PlayerDao;
import cz.fi.muni.pa165.yellow_yak.service.CompetitionService;
import cz.fi.muni.pa165.yellow_yak.service.GameService;
import cz.fi.muni.pa165.yellow_yak.service.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;

/**
 * Tests data loading.
 */
@ContextConfiguration(classes = {GamingManagementWithSampleDataConfiguration.class})
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class SampleDataLoadingFacadeTest extends AbstractTestNGSpringContextTests {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeTest.class);

    @Autowired
    public CompetitionDao competitionDao;

    @Autowired
    public PlayerDao playerDao;

    @Autowired
    public GameDao gameDao;

    // TODO Doplnit dao

    @Autowired
    public PlayerService playerService;

    @Autowired
    public GameService gameService;

    @Autowired
    public CompetitionService competitionService;

    // TODO Doplnit service

    @Autowired
    public SampleDataLoadingFacade sampleDataLoadingFacade;

    @PersistenceContext
    private EntityManager em;

    @Test
    public void createSampleData() throws IOException {
        log.debug("starting test");

        Assert.assertTrue(playerDao.findAll().size() > 0);
        Assert.assertTrue(playerService.findAll().size() > 0);

        // TODO doplnit test

        Assert.assertTrue(gameDao.findAll().size() > 0);
        Assert.assertTrue(gameService.findAll().size() > 0);

        Assert.assertTrue(competitionDao.findAll().size() > 0);
//        Assert.assertTrue(competitionService.findAll().size() > 0);
    }

}