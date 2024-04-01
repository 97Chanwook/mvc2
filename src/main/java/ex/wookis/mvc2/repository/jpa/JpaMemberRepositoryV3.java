package ex.wookis.mvc2.repository.jpa;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.domain.QMember;
import ex.wookis.mvc2.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static ex.wookis.mvc2.domain.QMember.member;

/**
 * JPA 와 QueryDSL 을 이용한 MemberRepository
 */
@Slf4j
@Transactional
public class JpaMemberRepositoryV3 implements MemberRepository {

    private final EntityManager em;
    private final JPAQueryFactory query;


    public JpaMemberRepositoryV3(EntityManager em) {
        this.em = em;
        this.query = new JPAQueryFactory(em);
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member findMember = em.find(Member.class, id);
        return Optional.ofNullable(findMember);
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
        String jpql = "select m from Member m";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        return query.getResultList();
    }

    //QueryDSL 사용 -> 조건이 있을 경우
    public List<Member> findMembers(String name) {
        return query.select(member)
                .from(member)
                .where(likeName(name))
                .fetch();
        // 단건 조회 -> fetchOne();
    }

    private BooleanExpression likeName(String name) {
        if (StringUtils.hasText(name)) {
            return member.name.like("%"+name+"%");
        }

        return null;
    }

    private BooleanExpression equalsLoginId(String loginId) {
        if (StringUtils.hasText(loginId)) {
            return member.name.eq(loginId);
        }

        return null;
    }
}
