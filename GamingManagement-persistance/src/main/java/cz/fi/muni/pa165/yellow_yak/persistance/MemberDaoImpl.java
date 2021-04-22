package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class MemberDaoImpl implements MemberDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Member m) {
        em.persist(m);
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    @Override
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    @Override
    public void remove(Member m) {
        em.remove(this.findById(m.getId()));
    }

    @Override
    public void update(Member m) {
        em.merge(m);
    }
}
