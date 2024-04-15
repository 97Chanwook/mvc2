package ex.wookis.mvc2.repository.jpa;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static ex.wookis.mvc2.domain.QMember.member;

@Slf4j
@Repository
@Transactional
public class CustomJpaMemberRepository implements MemberRepository {

    private final SpringDataJpaRepository repository;
    private final JPAQueryFactory query;

    public CustomJpaMemberRepository(SpringDataJpaRepository repository, EntityManager em) {
        this.repository = repository;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        return repository.save(member);
    }

    @Override
    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Member> findByUserLoginId(String userId) {
        Member findMemberByLoginId = query.select(member)
                .from(member)
                .where(equalsLoginId(userId))
                .fetchOne();

        return Optional.ofNullable(findMemberByLoginId);
    }

    @Override
    public List<Member> findAll() {
        return repository.findAll();
    }

    private BooleanExpression equalsLoginId(String loginId) {
        if (StringUtils.hasText(loginId)) {
            return member.loginId.eq(loginId);
        }

        return null;
    }
}
