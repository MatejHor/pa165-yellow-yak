package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import cz.fi.muni.pa165.yellow_yak.entity.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Implementation for competition DAO
 *
 * @author Lukas Mikula
 */
@Repository
@Transactional
public class CompetitionDaoImpl implements CompetitionDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Competition c) {
        em.persist(c);
    }

    @Override
    public List<Competition> findAll() {
        return em.createQuery("select c from Competition c", Competition.class).getResultList();
    }

    @Override
    public Competition findById(Long id) {
        return em.find(Competition.class, id);
    }

    @Override
    public void remove(Competition c) {
        em.remove(this.findById(c.getId()));
    }

    @Override
    public void update(Competition c) {
        em.merge(c);
    }

    @Override
    public List<Competition> findByName(String name) {
        return em.createQuery("select c from Competition c where c.name = :name", Competition.class)
                .setParameter("name", name)
                .getResultList();

    }

    @Override
    public List<Competition> findByGame(Game game) {
        return em.createQuery("select c from Competition c join c.game as g where g = :game",
                Competition.class)
                .setParameter("game", game)
                .getResultList();

    }

    @Override
    public Competition findOldest() {
        return em.createQuery("select c from Competition c order by c.createdAt",
                Competition.class).getResultList().get(0);
    }
}
