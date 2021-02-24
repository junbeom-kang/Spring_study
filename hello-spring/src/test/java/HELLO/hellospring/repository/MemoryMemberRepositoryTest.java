package HELLO.hellospring.repository;

import HELLO.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();
    @AfterEach//메서드가 끝날때마다 호출됨
    public void afterEach(){
        repository.clearstore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("준범");
        repository.save(member);
        Member result = repository.findById(member.getId()).get();//Optinal에서 값을 꺼낼땐 get()으로
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1=new Member();
        member1.setName("jb1");
        repository.save(member1);

        Member member2=new Member();//shift f6으로 같은변수 모두 변경가능
        member2.setName("jb2");
        repository.save(member2);

        Member result=repository.findByName("jb1").get();
        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1=new Member();
        member1.setName("jb1");
        repository.save(member1);

        Member member2=new Member();//shift f6으로 같은변수 모두 변경가능
        member2.setName("jb2");
        repository.save(member2);

        List<Member> result=repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }

}
