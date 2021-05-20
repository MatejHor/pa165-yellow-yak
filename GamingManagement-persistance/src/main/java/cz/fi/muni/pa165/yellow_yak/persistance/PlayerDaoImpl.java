package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Player;
import cz.fi.muni.pa165.yellow_yak.entity.Team;
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
    public void remove(Long id) {
        em.remove(this.findById(id));
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
    public List<Player> findByTeam(Team team) {
        return em.createQuery("select p from Player p join Member m on m.player = p where m.team = :team",
                Player.class)
                .setParameter("team", team)
                .getResultList();
    }

}
