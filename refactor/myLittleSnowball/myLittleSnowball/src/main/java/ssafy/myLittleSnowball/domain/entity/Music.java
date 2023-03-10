package ssafy.myLittleSnowball.domain.entity;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Music extends BaseEntity{

    @Id @GeneratedValue
    @Column(name = "music_id")
    private Long id;
    private String title;
    private String url;


    // == 생성 로직 == //


    // == 비즈니스 로직 == //
}
