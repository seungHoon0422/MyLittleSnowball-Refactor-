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
    private Long Id;
    private String name;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snowball_id")
    private Snowball mainSnowball;

    @OneToMany(mappedBy = "member")
    private List<Snowball> snowballList = new ArrayList<>();


    // for social login

    private String kakao_id;

    //    @Enumerated(EnumType.STRING)
//    private AuthProvider authProvider;
    private String refreshToken;

//    @Enumerated(EnumType.STRING)
//    private MemberRole role;
    @Column(length = 200)
    private String profileImageUrl;




}
