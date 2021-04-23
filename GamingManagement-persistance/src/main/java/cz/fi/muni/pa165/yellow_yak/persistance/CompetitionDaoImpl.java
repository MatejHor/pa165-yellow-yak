package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
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
        return em.createQuery("select c from Competition c where name = :name", Competition.class)
                .setParameter("name", name)
                .getResultList();

    }

    @Override
    public List<Competition> findByGame(Long gameId) {
        return em.createQuery("select c from Competition c join c.game as g where g = :gameId",
                Competition.class)
                .setParameter("gameId", gameId)
                .getResultList();

    }

    @Override
    public Competition findOldest() {
        return em.createQuery("select c from Competition c order by c.createdAt",
                Competition.class).getSingleResult();
    }
}
