package hello.core.Member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//junit사용
public class MemberServiceTest {
    MemberService memberService;
    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig=new AppConfig();
        memberService=appConfig.memberService();
    }
    @Test
    void join(){
        //given
        Member member = new Member(1L, "창훈", Grade.BASIC);
        //when
        memberService.join(member);
        Member member1 = memberService.findMember(member.getId());
        //then
        Assertions.assertThat(member).isEqualTo(member1);
    }


}
