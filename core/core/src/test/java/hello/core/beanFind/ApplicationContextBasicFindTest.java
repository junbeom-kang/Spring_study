package hello.core.beanFind;
import hello.core.AppConfig;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈이름으로 찾기")
    void findByBeanName(){
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        //MemberService memberService = ac.getBean("memberService", MemberServiceImpl.class); 도 가능

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }
    @Test
    @DisplayName("빈이름으로 찾기 실패")
    void findByBeanNameX(){
        org.junit.jupiter.api.Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                ()->ac.getBean("xxx", MemberService.class));
    }
    @Test
    @DisplayName("빈이름으로 찾기")
    void findByBeanType(){
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }



}
