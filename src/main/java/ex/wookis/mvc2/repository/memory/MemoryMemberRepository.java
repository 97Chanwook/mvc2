package ex.wookis.mvc2.repository.memory;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByUserLoginId(String userId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(userId))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }
}
