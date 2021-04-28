package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Matej Horniak
 */
@Repository
@Transactional
public class GameDaoImpl implements GameDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Game g) {
        em.persist(g);
    }

    @Override
    public List<Game> findAll() {
        return em.createQuery("select g from Game g", Game.class).getResultList();
    }

    @Override
    public Game findById(Long id) {
        return em.find(Game.class, id);
    }

    @Override
    public void remove(Game g) {
        em.remove(this.findById(g.getId()));
    }

    @Override
    public void update(Game g) {
        em.merge(g);
    }

    @Override
    public Game findByName(String name) {
        return em.createQuery("select g from Game g where g.name LIKE :gameName", Game.class)
                .setParameter("gameName", name)
                .getSingleResult();
    }
}
