package HELLO.hellospring.service;

import HELLO.hellospring.domain.Member;
import HELLO.hellospring.repository.MemberRepository;
import HELLO.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Transactional
public class MemberService {//ctrl shift T로 Test생성가능
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입 ,중복회원 불가
     */
    public Long join(Member member){
        checkDuplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void checkDuplicate(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{//null이 아니고 값이 있으면 동작한다.optional의 메서드
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                } );
    }
    /**
     *전체 회원 조회
     */
    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
