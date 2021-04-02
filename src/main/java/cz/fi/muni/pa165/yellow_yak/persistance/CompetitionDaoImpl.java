package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Lukas Mikula
 */
public class CompetitionDaoImpl implements CompetitionDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Competition e) {
        em.persist(e);
    }

    @Override
    public List<Competition> findAll() {
        return em.createQuery("select e from Competition e", Competition.class).getResultList();
    }

    @Override
    public Competition findById(Long id) {
        return em.find(Competition.class, id);
    }

    @Override
    public void remove(Competition e) {
        em.remove(e);
    }

    @Override
    public void update(Competition e) {
        em.merge(e);
    }

    @Override
    public List<Competition> findByName(String name) {
        return em.createQuery("select e from Competition e where name = :name", Competition.class)
                .setParameter("name", name)
                .getResultList();

    }
}
