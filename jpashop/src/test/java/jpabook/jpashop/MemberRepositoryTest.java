package jpabook.jpashop;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
/*
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Rollback(false)
public class MemberRepositoryTest {
    @Autowired MemberRepository memberRepository;
    @Test
    public void 테스트() throws Exception {
        //given
        Member member= new Member();
        member.setUsername("준범");
        //when
        Long saveMember = memberRepository.save(member);
        Member byId = memberRepository.findById(saveMember);
        //then
        assertThat(byId).isEqualTo(member);
        assertThat(member.getUsername()).isEqualTo(byId.getUsername());
        assertThat(member).isEqualTo(byId);
        //같은 영속성 컨텍스트 안에서는 식별자(id)값이 같으면 같은엔티티로 식별한다

    }

}

 */