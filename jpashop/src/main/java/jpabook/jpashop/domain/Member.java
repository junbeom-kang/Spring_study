package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {
    @Id @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "member")//order의 member에서 mapping된 거울일뿐 read only가 되버림
    private List<Order> orders=new ArrayList<>();
}
