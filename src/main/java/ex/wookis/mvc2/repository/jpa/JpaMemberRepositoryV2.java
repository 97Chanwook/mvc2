package ex.wookis.mvc2.repository.jpa;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import jakarta.persistence.EntityManager;
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
//@Repository
@Transactional
@RequiredArgsConstructor
public class JpaMemberRepositoryV2 implements MemberRepository {

    private final SpringDataJpaRepository repository;

    @Override
    public Member save(Member member) {
        repository.save(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Member> findByUserLoginId(String userId) {
        return repository.findByLoginId(userId);
    }

    @Override
    public List<Member> findAll() {
        return repository.findAll();
    }
}
