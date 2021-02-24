package HELLO.hellospring.repository;


import HELLO.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);  //Optional null이 나올 수 있을때 감싸서 나오게해주는 것
    Optional<Member> findByName(String name);
    List<Member> findAll();

}
