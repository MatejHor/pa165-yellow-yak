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
 * @author Matej Knazik
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest extends AbstractTestNGSpringContextTests {

    @Autowired
    @InjectMocks
    private TeamFacade teamFacade;


    @BeforeClass
    public void init() throws ServiceException {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createTeamNullTest() {
        teamFacade.create(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void removeTeamNullTest() {
        teamFacade.remove(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByIdNullTest() {
        teamFacade.findById(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void findByNameNullTest() {
        teamFacade.findByName(null);
    }


}
