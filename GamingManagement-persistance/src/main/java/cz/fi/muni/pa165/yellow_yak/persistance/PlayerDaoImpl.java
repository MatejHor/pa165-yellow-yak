package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Matej Horniak
 */
@Repository
@Transactional
public class PlayerDaoImpl implements PlayerDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Player p) {
        em.persist(p);
    }

    @Override
    public List<Player> findAll() {
        return em.createQuery("select p from Player p", Player.class).getResultList();
    }

    @Override
    public Player findById(Long id) {
        return em.find(Player.class, id);
    }

    @Override
    public void remove(Player p) {
        em.remove(this.findById(p.getId()));
    }

    @Override
    public void update(Player p) {
        em.merge(p);
    }

    @Override
    public List<Player> findByUsername(String username) {
        return em.createQuery("select p from Player p where p.username LIKE :userName", Player.class)
                .setParameter("userName", username)
                .getResultList();
    }

    @Override
    public List<Player> findByTeam(Long teamId) {
        return em.createQuery("select p from Team t join Player p where t.id = :teamId",
                Player.class)
                .setParameter("teamId", teamId)
                .getResultList();
    }

}
