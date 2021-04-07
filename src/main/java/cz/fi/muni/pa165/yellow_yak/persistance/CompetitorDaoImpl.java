package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Competitor;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author oreqizer
 */
@Service
@Transactional
public class CompetitorDaoImpl implements CompetitorDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Competitor c) {
        em.persist(c);
    }

    @Override
    public List<Competitor> findAll() {
        return em.createQuery("select c from Competitor c", Competitor.class).getResultList();
    }

    @Override
    public Competitor findById(Long id) {
        return em.find(Competitor.class, id);
    }

    @Override
    public void remove(Competitor c) {
        em.remove(this.findById(c.getId()));
    }

    @Override
    public void update(Competitor c) {
        em.merge(c);
    }

    @Override
    public List<Competitor> getByCompetition(Competition competition) {
        return em.createQuery("select c from Competitor c where c.competition = :competition", Competitor.class)
                .setParameter("competition", competition)
                .getResultList();
    }

    @Override
    public List<Competitor> getByTeam(Team team) {
        return em.createQuery("select c from Competitor c where c.team = :team", Competitor.class)
                .setParameter("team", team)
                .getResultList();
    }
}
