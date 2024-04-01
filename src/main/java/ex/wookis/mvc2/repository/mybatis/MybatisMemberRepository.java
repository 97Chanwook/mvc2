package ex.wookis.mvc2.repository.mybatis;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class MybatisMemberRepository implements MemberRepository {

    private final MemberMapper memberMapper;

    @Override
    public Member save(Member member) {
        memberMapper.save(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return memberMapper.findById(id);
    }

    @Override
    public Optional<Member> findByUserLoginId(String userId) {
        return memberMapper.findByUserLoginId(userId);
    }

    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }
}
