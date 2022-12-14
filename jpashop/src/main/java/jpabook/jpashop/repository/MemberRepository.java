package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {


    private final EntityManager em;
    // JPA의 Entity Manager를 Spring 이 생성해서 주입해준다.
    // @RequiredArgsConsttructor로 자동 주입 가능
//    @PersistenceContext
//    private EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }


    /*
    SQL은 테이블을 대상으로 쿼리 작성
    JPQL은 Entity를 대상으로 쿼리 작성
     */
    public List<Member> findAll() {
        // param1 : JPQL, apram2 : 반환 타입
        return em.createQuery("select m from member m", Member.class)
                .getResultList();   // member를 result

    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
