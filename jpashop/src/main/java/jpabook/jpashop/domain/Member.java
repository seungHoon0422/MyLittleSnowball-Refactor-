package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded   // 내장타입
    private Address address;

    @OneToMany (mappedBy = "member")     // 연관관계의 주인을 명시해준다!  order table에 있는 member column이 연관관계의 주인
    private List<Order> orders = new ArrayList<>();
}
