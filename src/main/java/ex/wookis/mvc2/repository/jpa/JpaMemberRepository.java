package ex.wookis.mvc2.repository.jpa;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * JPA 를 이용한 MemberRepository
 */
@Slf4j
@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberRepository implements MemberRepository {

    private final EntityManager em;

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
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(userId))
                .findFirst();
    }

    @Override
    public List<Member> findAll() {
        String jpql = "select m from Member m";
        TypedQuery<Member> query = em.createQuery(jpql, Member.class);
        return query.getResultList();
    }
}
