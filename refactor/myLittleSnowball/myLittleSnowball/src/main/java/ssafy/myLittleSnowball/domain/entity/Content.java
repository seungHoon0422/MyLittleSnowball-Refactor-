package ssafy.myLittleSnowball.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Content {

    @Id @GeneratedValue
    @Column(name = "content_id")
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    Board board;

    String text;
    String imageUrl;

    // == 생성 로직 == //
    public static Content createContent(Board board, String text, String imageUrl) {
        Content content = new Content();
        content.setContent(text, imageUrl);
        return content;
    }


    // == 비즈니스 로직 == //
    void changeContent(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }

    // == 연관관계 메서드 == //
    public void setContent(String text, String imageUrl) {
        this.text = text;
        this.imageUrl = imageUrl;
    }


}
