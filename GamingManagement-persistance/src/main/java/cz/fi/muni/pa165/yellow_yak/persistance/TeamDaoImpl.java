package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Team;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Matej Knazik
 */
@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Team team) {
        em.persist(team);
    }

    @Override
    public void remove(Team team) {
        em.remove(this.getById(team.getId()));
    }

    @Override
    public void update(Team team) {
        em.merge(team);
    }

    @Override
    public List<Team> getAll() {
        return em.createQuery("select team from Team team", Team.class).getResultList();
    }

    @Override
    public Team getById(Long id) {
        return em.find(Team.class, id);
    }

    @Override
    public void addPlayer(Long teamId, Long playerId) {

    }

    @Override
    public void removePlayer(Long teamId, Long playerId) {

    }

    @Override
    public List<Team> getByName(String name) {
        try {
            return em.createQuery("select team from Team team where name = :name", Team.class)
                    .setParameter("name", name)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Team> getByCompetition(Long competitionId) {
        try {
            return em.createQuery("select t from Competition c join Team t where c.id = :competitionId", Team.class)
                    .setParameter("competitionId", competitionId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Team> getByPlayer(Long playerId) {
        try {
            return em.createQuery("select t from Player p join Team t where p.id = :playerId", Team.class)
                    .setParameter("playerId", playerId)
                    .getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

}
