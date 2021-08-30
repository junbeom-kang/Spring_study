package hello.core.order;

import hello.core.AppConfig;
import hello.core.Member.*;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        orderService = appConfig.orderService();
        memberService= appConfig.memberService();
    }

    @Test
    void createOrder(){
        Long memberId=1L;
        Member member = new Member(memberId, "준범", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "집행검", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }

}
