package hello.core.Member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long,Member> store=new HashMap<>();
    //사실은 ConcurrentHashMap을 써야함(동시성때문에)
    @Override
    public void save(Member member) {
        store.put(member.getId(),member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
