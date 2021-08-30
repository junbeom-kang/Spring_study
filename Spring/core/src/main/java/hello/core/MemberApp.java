package hello.core;

import hello.core.Member.Grade;
import hello.core.Member.Member;
import hello.core.Member.MemberService;
import hello.core.Member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig=new AppConfig();
//        MemberService memberService=appConfig.memberService();
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "준범", Grade.VIP);
        memberService.join(member);
        Member member1 = memberService.findMember(member.getId());
        System.out.println("member.getName() = " + member.getName());
        System.out.println("find = " + member1.getName());
    }
}
