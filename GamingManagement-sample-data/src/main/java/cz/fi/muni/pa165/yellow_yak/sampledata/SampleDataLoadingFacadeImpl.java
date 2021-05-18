package cz.fi.muni.pa165.yellow_yak.sampledata;

import cz.fi.muni.pa165.yellow_yak.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * Loads some sample data to populate the eshop database.
 */
@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    private CompetitionService competitionService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private GameService gameService;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private TeamService teamService;

    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException {
        // TODO nacitanie dat
    }
}
