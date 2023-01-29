package ssafy.myLittleSnowball.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ssafy.myLittleSnowball.domain.collection.Decoration;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Snowball extends BaseEntity{


    @Id
    @GeneratedValue
    @Column(name = "snowball_id")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_id")
    private Music music;

    @OneToOne(mappedBy = "snowball")
    private Board board;

    private Long decorationId;



    // == 생성 로직 == //
     public static Snowball createSnowball(Member member, Music music, Decoration decoration) {
        Snowball snowball = new Snowball();
        snowball.setMember(member);
        snowball.setMusic(music);
        snowball.setBoard(Board.createBoard(member, snowball), member);
        snowball.setDecorationId(decoration.getId());

        return snowball;
    }

    // == 비즈니스 로직 == //

    public void setMember(Member member) {
        this.member = member;
        member.getSnowballs().add(this);
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setBoard(Board board, Member member) {
        this.board = board;
        board.setSnowball(this);
        board.setMember(member);
    }

    public void setDecorationId(Long decorationId) {
        this.decorationId = decorationId;
    }



}
