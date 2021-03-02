package hello.core.scan;


import hello.core.AutoAppConfig;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.discount.DiscountPolicy;
import lombok.Getter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBean {
    ApplicationContext ac= new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
    @Test
    void allBeanCheck(){

        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        Member member=new Member(1L,"junbeom", Grade.VIP);

        int discountPrice=bean.discount(member,10000,"fixDiscountPolicy");
        assertThat(discountPrice).isEqualTo(1000);


    }
    static class DiscountService{
        private final Map<String, DiscountPolicy> policyMap;
        private final List<DiscountPolicy> policyList;
        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policyList);
        }


        public int discount(Member member, int price, String discountCode) {
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }
    }

}
