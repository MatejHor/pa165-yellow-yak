package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competitor;
import cz.fi.muni.pa165.yellow_yak.entity.Score;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

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
        return em.createQuery("select c from Score c", Score.class).getResultList();
    }

    @Override
    public Score findById(Long id) {
        return em.find(Score.class, id);
    }

    @Override
    public void remove(Score s) {
        em.remove(s);
    }

    @Override
    public void update(Score s) {
        em.merge(s);
    }

    @Override
    public Score getByCompetitor(Competitor competitor) {
        return em.createQuery("select s from Score s where s.competitor = :competitor", Score.class)
                .setParameter("competitor", competitor)
                .getSingleResult();
    }
}
