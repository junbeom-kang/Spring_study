package jpabook.jpashop.api;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("/api/v1/members")// 엔티티를 바깥으로 들어내므로 안 좋은 방법임
    public createMemberResponse saveMemberV1(Member member) {
        Long memberId = memberService.join(member);
        return new createMemberResponse(memberId);
    }
    @PostMapping("/api/v2/members")// 엔티티를 바깥으로 들어내므로 안 좋은 방법임
    public createMemberResponse saveMemberV2(Request request) {
        Member member = new Member();
        member.setName(request.getName());
        Long memberId = memberService.join(member);
        return new createMemberResponse(memberId);

    }
    @Data
    static class createMemberResponse{
        private Long id;
        public createMemberResponse(Long id) {
            this.id = id;
        }
    }

    @Data
    static class Request {
        private String name;
    }
}
