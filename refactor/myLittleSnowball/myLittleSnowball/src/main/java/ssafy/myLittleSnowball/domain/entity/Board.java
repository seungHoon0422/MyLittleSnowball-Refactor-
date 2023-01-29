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
public class Board extends BaseEntity{


    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "snowball_id")
    private Snowball snowball;

    @OneToMany(mappedBy = "board")
    private List<Content> contents = new ArrayList<>();


    // == 생성 로직 == //
   public static Board createBoard(Member member, Snowball snowball) {
       Board board = new Board();
       board.setMember(member);
       board.setSnowball(snowball);
       return board;
   }



    // == 연관관계 메서드 == //
    public void setSnowball(Snowball snowball) {
        this.snowball = snowball;
    }
    void setMember(Member member) {
        this.member = member;
    }
}
