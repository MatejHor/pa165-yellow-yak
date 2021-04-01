package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Game p) {
        em.persist(p);
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
    public void remove(Game p) {
        em.remove(p);
    }

    @Override
    public void update(Game p) {
        em.merge(p);
    }

    @Override
    public List<Game> findByName(String name) {
        return em.createQuery("select g from Game g where g.name LIKE :gameName", Game.class)
                .setParameter("gameName", name)
                .getResultList();
    }
}
