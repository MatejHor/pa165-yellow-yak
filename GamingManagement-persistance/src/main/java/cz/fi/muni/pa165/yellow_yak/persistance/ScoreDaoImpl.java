package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Score;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author oreqizer
 */
@Service
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
                                                         Long competitionId,
                                                         LocalDateTime createdAt) {
        return em.createQuery(
                "select s from Score s" +
                        " join s.player as p join s.competition as c " +
                        "where p.id = :playerId and c.id = :competitionId and (:createdAt is null or s.createdAt = :createdAt)",
                Score.class)
                .setParameter("playerId", playerId)
                .setParameter("competitionId", competitionId)
                .setParameter("createdAt", createdAt)
                .getResultList();
    }

}
