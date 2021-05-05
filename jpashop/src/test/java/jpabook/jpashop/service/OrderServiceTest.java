package jpabook.jpashop.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    @PersistenceContext
    EntityManager em;
    @Autowired OrderService orderService;
    @Autowired OrderRepository orderRepository;
    @Test
    public void 상품주문() throws Exception {
        //given
        Member member = getMember();
        Book book = getBook("JPA", 10000, 10);
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);
        Order getOrder = orderRepository.findOne(orderId);
        //then
        assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        assertThat(getOrder.getTotalPrice()).isEqualTo(20000);
        assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        assertThat(book.getStockQuantity()).isEqualTo(8);

    }
    @Test
    public void gson_테스트() throws Exception {
        Member member=new Member();
        member.setName("준범");
        Gson gson=new GsonBuilder().create();
        String s=gson.toJson(member);
        System.out.println(s);

    }



    @Test(expected = NotEnoughStockException.class)
    public void 상품주문_재고수량_초과() throws Exception {
        //given
        Member member = getMember();
        Book book = getBook("JPA", 10000, 10);
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 11);

        //then
        fail("재고수량 예외가 발생해야함");
    }
    @Test
    public void 주문취소() throws Exception {
        //given
        Member member = getMember();
        Book book = getBook("JPA", 10000, 10);
        Long orderId = orderService.order(member.getId(), book.getId(), 3);
        //when
        orderService.cancelOrder(orderId);

        //then
        assertThat(book.getStockQuantity()).isEqualTo(10);
        assertThat(orderRepository.findOne(orderId).getStatus()).isEqualTo(OrderStatus.CANCEL);

    }


    private Book getBook(String name, int price, int stockQuantity) {
        Book book=new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        Member member = new Member();
        member.setName("준범");
        member.setAddress(new Address("안양시", "귀인로", "193"));
        em.persist(member);
        return member;
    }

}