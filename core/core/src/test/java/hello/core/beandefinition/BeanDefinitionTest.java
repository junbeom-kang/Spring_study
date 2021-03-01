package hello.core.beandefinition;

import hello.core.AppConfig;
import hello.core.Member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

public class BeanDefinitionTest {
    ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);


    @Test
    void 싱글톤_테스트() {
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        Assertions.assertThat(memberService).isSameAs(memberService1);

    }

    @Test
    void 오브젝트클래스_빈_출력테스트() {
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "value" + beansOfType.get(key));
        }
    }

    @Test
    void Appconfig_빈_출력테스트() {
        Map<String, AppConfig> beansOfType = ac.getBeansOfType(AppConfig.class);
        for (String s : beansOfType.keySet()) {
            System.out.println(beansOfType.get(s));
        }
    }
}
