package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author oreqizer, Matej Horniak
 */
@Repository
@Transactional
public class ScoreDaoImpl implements ScoreDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Score c) {
        em.persist(c);
    }

    @Override
    public List<Score> findAll() {
        return em.createQuery("select c from Score c",
                Score.class).getResultList();
    }

    @Override
    public Score findById(Long id) {
        return em.find(Score.class, id);
    }

    @Override
    public void remove(Score s) {
        em.remove(this.findById(s.getId()));
    }

    @Override
    public void update(Score s) {
        em.merge(s);
    }

    @Override
    public List<Score> findByPlayerAndCompetitionAndDate(Long playerId,
                                                         List<Competition> competitions,
                                                         LocalDateTime createdAt) {
        return em.createQuery(
                "select s from Score s" +
                        " join s.player as p join s.competition as c " +
                        "where p.id = :playerId and c in :competitions and s.createdAt = :createdAt",
                Score.class)
                .setParameter("playerId", playerId)
                .setParameter("competitions", competitions)
                .setParameter("createdAt", createdAt)
                .getResultList();
    }

    @Override
    public List<Score> findByPlayerAndGame(Long playerId, Long gameId) {
        return em.createQuery(
                "select s from Score s " +
                        "join s.competition as c " +
                        "join c.game as g " +
                        "join s.player as p " +
                        "where g.id = :gameId and p.id = :playerId", Score.class)
                .setParameter("playerId", playerId)
                .setParameter("gameId", gameId)
                .getResultList();
    }

    @Override
    public List<Score> findByCompetition(Long competitionId) {
        return em.createQuery(
                "select s from Score s " +
                        "join s.competition as c " +
                        "where c.id = :competitionId", Score.class)
                .setParameter("competitionId", competitionId)
                .getResultList();
    }

    @Override
    public List<Score> findCompetitionResults(Long competitionId) {
        return em.createQuery(
                "select s from Score s " +
                        "join s.competition as c " +
                        "where c.id = :competitionId " +
                        "and s.result > 0 " +
                        "and s.result is not null " +
                        "order by s.result desc", Score.class)
                .setParameter("competitionId", competitionId)
                .getResultList();
    }
}
