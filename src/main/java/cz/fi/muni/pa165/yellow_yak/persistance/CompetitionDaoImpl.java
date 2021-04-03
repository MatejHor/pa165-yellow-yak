package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Competition;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Lukas Mikula
 */
@Service
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
}
