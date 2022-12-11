package jpabook.jpashop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {

    @PersistenceContext     // spring boot가 entitiy manager를 자동으로 생성
    private EntityManager em;

    // command와 query를 분리시켜라! -> return에 member를 넘길 경우 side effect가 발생할 수 있다.
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

}
