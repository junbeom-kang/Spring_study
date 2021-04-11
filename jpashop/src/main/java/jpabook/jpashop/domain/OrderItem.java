package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access= AccessLevel.PROTECTED) //다른 생성방법을 막아버리기

public class OrderItem {
    @Id @GeneratedValue
    @Column(name="order_item_id")
    private Long id;
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name="order_id")
    private Order order;

    private int orderPrice; //주문가격
    private int count; //주문수량


    //==생성 메서드==// item에 가격이 들어있을 수 있지만 할인같은 정책 넣을때 이게 좋음
    public static OrderItem createOrderItem(Item item,int orderPrice,int count) {
        OrderItem orderItem=new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        this.getItem().addStock(count);
    }
    public int totalPrice() {
        return orderPrice*count;
    }

}
