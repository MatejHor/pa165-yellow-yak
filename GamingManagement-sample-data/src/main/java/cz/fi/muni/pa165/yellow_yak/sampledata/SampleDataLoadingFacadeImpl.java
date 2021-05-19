package cz.fi.muni.pa165.yellow_yak.sampledata;

import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

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
        Player player_1 = player("player_1", "player_1@gmail.com");
        Player player_2 = player("player_2", "player_2@gmail.com");
        Player player_3 = player("player_3", "player_3@gmail.com");
        Player player_4 = player("player_4", "player_4@gmail.com");
        Player player_5 = player("player_5", "player_5@gmail.com");
    }

    private Player player(String username, String email) {
        return playerService.create(username, email);
    }
}
