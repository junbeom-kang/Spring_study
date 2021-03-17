package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    @PersistenceContext
    private EntityManager em;
    //저장
    public void save(Member member) {
        em.persist(member);
    }
    //id로 하나찾기
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }
    //모든리스트 반환
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
    //이름으로 찾기
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name=:name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
