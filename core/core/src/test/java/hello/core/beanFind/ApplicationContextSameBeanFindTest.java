package hello.core.beanFind;

import hello.core.Member.MemberRepository;
import hello.core.Member.MemoryMemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ApplicationContextSameBeanFindTest {
    AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(SameBeanConfig.class);
    @Test
    void findByName(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                ()->ac.getBean(MemberRepository.class));
    }
    @Test
    @DisplayName("검색한타입의 모든 Bean 조회하기")
    void findByType(){
        Map<String, MemberRepository> beansOfType = ac.getBeansOfType(MemberRepository.class);
        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key+"value = "+beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }




    @Configuration
    static class SameBeanConfig{
        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }@Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
