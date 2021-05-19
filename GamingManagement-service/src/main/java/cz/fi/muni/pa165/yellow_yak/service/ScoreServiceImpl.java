package cz.fi.muni.pa165.yellow_yak.service;

import cz.fi.muni.pa165.yellow_yak.dto.ScoreDTO;
import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import cz.fi.muni.pa165.yellow_yak.persistance.ScoreDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author Matej Horniak
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    private ScoreDao scoreDao;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private CompetitionService competitionService;


    @Override
    public List<Score> findByPlayerAndCompetitionAndDate(Long playerId,
                                                         List<Competition> competitions,
                                                         LocalDate createdAt) {
        return scoreDao.findByPlayerAndCompetitionAndDate(playerId, competitions, createdAt);
    }

    @Override
    public Score create(Long competitionId, Long playerId) {
        Player player = playerService.findById(playerId);
        Competition competition = competitionService.findById(competitionId);
        if (player != null && competition != null) {
            Score score = new Score();
            score.setPlayer(player);
            score.setCompetition(competition);
            scoreDao.create(score);
            return score;
        }
        return null;
    }

    @Override
    public void remove(Long id) {
        if (id == null)
            return;
        scoreDao.remove(scoreDao.findById(id));
    }

    @Override
    public Score findById(Long id) {
        if (id == null)
            return null;
        return scoreDao.findById(id);
    }

    @Override
    public Score setResult(Long id, int result) {
        Score score = scoreDao.findById(id);
        score.setResult(result);

        scoreDao.update(score);

        List<Score> results = scoreDao.findCompetitionResults(score.getCompetition().getId());
        IntStream.range(0, results.size()).forEach(i -> {
            Score s = results.get(0);
            s.setPlacement(i + 1);
            scoreDao.update(s);
        });

        return score;
    }

    @Override
    public List<Score> findByPlayerAndGame(Long playerId, Long gameId) {
        return scoreDao.findByPlayerAndGame(playerId,gameId);
    }

    @Override
    public List<Score> findByCompetition(Long competitionId) {
        return scoreDao.findByCompetition(competitionId);
    }
}
