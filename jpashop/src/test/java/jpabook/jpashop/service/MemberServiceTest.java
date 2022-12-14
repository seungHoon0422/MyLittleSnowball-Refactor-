package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

// spring 과 엮어서 테스트하기 위해서는 @Runwith, @SpringBootTest 필요
@RunWith(SpringRunner.class) // junit 시행할 때 spring과 엮어서 실행
@SpringBootTest // spring boot를 띄운 상태로 실행 -> 안해놓으면 Autowired 실패
@Transactional // test가 끝나면 roll back
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    @Rollback(false)
    // Test에서 default setting은 @Rollback(true)로 되어있다.
    // flush() commit() 이 진행되지 않는다.
    public void 회원가입() {

        // given
        Member member = new Member();
        member.setName("kim");

        // when
        Long savedId = memberService.join(member);
//        em.flush(); // flush()를 실행하면 영속성 컨텍스트에 있는 쿼리를 날리므로 insert 쿼리 실행


        // then
        assertEquals(member, memberRepository.findOne(savedId));
        // 같은 트랜잭션 안에서 동작하기 때문에 영속성 컨텍스트 안에 같은 객체가 들어가있다.
    }

    // 코드에서 try catch를 통해 예외를 잡지 않고 expected를 통해 catch 가능
    @Test(expected = IllegalStateException.class)
    public void 중복회원예외() {

        // given
        Member member1 = new Member();
        member1.setName("kim1");
        Member member2 = new Member();
        member2.setName("kim1");

        // when
        memberService.join(member1);
        memberService.join(member2); // 예외가 발생하는 상황
//        try {
//            memberService.join(member2); // 예외가 발생하는 상황
//        } catch (IllegalStateException e) {
//            return;
//        }

        // then
        fail("예외가 발생해야 한다.");
    }


}
