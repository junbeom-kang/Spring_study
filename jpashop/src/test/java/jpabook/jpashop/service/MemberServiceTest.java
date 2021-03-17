package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest //두개가있어야 스프링부트를 올려서 테스트가능하다.
@Transactional //데이터 변경을 위해 (롤백해줌)
public class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        Member member=new Member();
        member.setName("준범");
        //when
        Long memberId = memberService.join(member);

        //then
        Assertions.assertThat(member).isEqualTo(memberService.findOne(memberId));
        //영속성컨테스트에서는 PK(id)가 같으면 같은 놈으로됨
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        Member member2 = new Member();
        member1.setName("창훈");
        member2.setName("창훈");
        //when
        memberService.join(member1);
        //then
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

}