package cz.fi.muni.pa165.yellow_yak.persistance;

import cz.fi.muni.pa165.yellow_yak.entity.Member;

import java.util.List;

public interface MemberDao {
    /**
     * Create new member
     * @param m member to create
     */
    void create(Member m);

    /**
     * Return all member in db
     * @return List of member
     */
    List<Member> findAll();

    /**
     * Return member with specific id
     * @param id specific id
     * @return Member
     */
    Member findById(Long id);

    /**
     * Remove member
     * @param m member to remove
     */
    void remove(Member m);

    /**
     * Update member
     * @param m member to update
     */
    void update(Member m);
}
