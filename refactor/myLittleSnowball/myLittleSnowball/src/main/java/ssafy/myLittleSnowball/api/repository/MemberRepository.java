package ssafy.myLittleSnowball.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ssafy.myLittleSnowball.domain.entity.Member;

@Repository
@Transactional(readOnly = true)
public interface MemberRepository extends JpaRepository<Member, Long> {


    // 회원가입
    @Transactional
    @Override
    <S extends Member> S save(S member);

    // 로그인
    Member findByEmail(String email);

    // 닉네임 변경
    @Transactional
    @Modifying
    @Query(value = "UPDATE Member m SET m.nickname = :nickname WHERE m.member_id = :member_id", nativeQuery = true)
    void updateNickname(@Param("member_id") Long memberId, @Param("nickname") String nickname);


    // refresh token 갱신
    @Transactional
    @Modifying
    @Query(value = "UPDATE Member m SET m.refresh_token = :refresh_token WHERE m.member_id = :member_id", nativeQuery = true)
    void updateRefreshToken(@Param("member_id") Long memberId, @Param("refresh_token") String refreshToken);

}
