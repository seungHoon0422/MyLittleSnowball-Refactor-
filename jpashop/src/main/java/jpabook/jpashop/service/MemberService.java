package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
// JPA의 동작 코드는 기본적으로 Transaction안에서 이루어져야 한다.
// spring과 관련된 의존관계가 많이 설정되어있으므로 javax보다는 spring Annotation을 사용하자
// JPA가 조회하는 쿼리에 대해서는 readOnly = true 옵션을 부여해 성능 최적화
// 영속성컨텍스트를 더티체킹을 하지 않는다/
// DB에 읽기전용 트렌젝션이라는 정보를 알려주고, 리소스를 절약 가능
// ** 결론
// -- DB가수정되는 Transaction에는 (readOnly = false)를 부여

@Transactional(readOnly = true)
@RequiredArgsConstructor // final이 붙은 필드만 생성자로 만들어준다.
//@AllArgsConstructor   // 필드에 존재하는 모든 변수들에 대한 생성자를 만들어준다.
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional // (readOnly = false) 기본 옵션이 false
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검증
        memberRepository.save(member);
        return member.getId();
        
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }


    // 회원 전체 조회
    @Transactional(readOnly = true)
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
