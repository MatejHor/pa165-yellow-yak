package cz.fi.muni.pa165.yellow_yak.facade;

import cz.fi.muni.pa165.yellow_yak.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author Matej Horniak, oreqizer
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class ScoreFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private ScoreFacade scoreFacade;

    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullPlayerId() {
        scoreFacade.create(1L, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullCompetitionId() {
        scoreFacade.create(null, 1L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeNullId() {
        scoreFacade.remove(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullId() {
        scoreFacade.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void setResultNullId() {
        scoreFacade.setResult(null, 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByPlayerGameNullGameId() {
        scoreFacade.findByPlayerGame(1L, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByPlayerGameNullPlayerId() {
        scoreFacade.findByPlayerGame(null, 1L);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByCompetitionNullCompetitionId() {
        scoreFacade.findByCompetition(null);
    }

}
