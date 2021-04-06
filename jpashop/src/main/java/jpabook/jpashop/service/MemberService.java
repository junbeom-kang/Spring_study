package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    /*
    회원가입
     */
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member.getName());
        memberRepository.save(member);
        return member.getId();
    }
    /*
    중복회원검사
     */
    private void validateDuplicateMember(String name) {
        if (!memberRepository.findByName(name).isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }//정말 동시에 같은이름을 가진 두명이 가입하면 오류 실무에서는 최후까지해주어야함
    }
    /*
    회원전체 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    /*
    회원하나조회
     */
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member findMember = memberRepository.findOne(id);
        findMember.setName(name);

    }
}
