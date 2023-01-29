package ssafy.myLittleSnowball.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long Id;
    private String nickname;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snowball_id")
    private Snowball mainSnowball;


    @OneToMany(mappedBy = "member")
    private List<Snowball> snowballs = new ArrayList<>();


    // for social login

    private String kakaoId;

    //    @Enumerated(EnumType.STRING)
//    private AuthProvider authProvider;
    private String refreshToken;

//    @Enumerated(EnumType.STRING)
//    private MemberRole role;
    @Column(length = 200)
    private String profileImageUrl;

    // == 생성 로직 == //
    public static Member createMember(String nickname, String email, String kakaoId, String profileImageUrl) {
        Member member = new Member();
        member.setBaseInformation(nickname, email, kakaoId, profileImageUrl);
        member.setDefaultSnowball();
        return member;
    }

    private void setDefaultSnowball() {
//        this.mainSnowball = Snowball.createDefaultSnowball(this);
    }


    // == 비즈니스 로직 == //
    void changeNickname(String nickname) {
        this.nickname = nickname;
    }


    // == setter == //
    public void setBaseInformation(String nickname, String email, String kakaoId, String profileImageUrl) {
        this.nickname = nickname;
        this.email = email;
        this.kakaoId = kakaoId;
        this.profileImageUrl = profileImageUrl;
    }


}
