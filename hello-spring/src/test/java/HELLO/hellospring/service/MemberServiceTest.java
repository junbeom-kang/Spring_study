package HELLO.hellospring.service;

import HELLO.hellospring.domain.Member;
import HELLO.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        memberRepository=new MemoryMemberRepository();
        memberService=new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach() {
        memberRepository.clearstore();
    }
    @Test
    void join() {//give,when,then 문법 사용
        //given
        Member member=new Member();
        member.setName("hello");
        //when
        Long saveId = memberService.join(member);
        
        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(findMember.getName()).isEqualTo(member.getName());

    }
    @Test
    void 중복회원검증(){
        //given
        Member new1=new Member();
        new1.setName("창훈");
        Member new2=new Member();
        new2.setName("창훈");
        //when
        memberService.join(new1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(new2));
        //혹시 확인하고싶으면
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        //이걸 넘겨서 실행했을때 Illegal.class의 오류가 발생해야돼 라는 문법
        /*
        try {
            memberService.join(new2);
        } catch (IllegalStateException e) {
            assertThat("이미 존재하는 회원입니다").isEqualTo(e.getMessage());
        }
         */


    }
    @Test
    void findMember() {
    }

    @Test
    void findOne() {
    }
}