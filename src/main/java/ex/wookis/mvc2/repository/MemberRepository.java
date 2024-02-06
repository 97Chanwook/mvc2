package ex.wookis.mvc2.repository;

import ex.wookis.mvc2.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);

    Member findById(Long id);

    Optional<Member> findByUserLoginId(String userId);

    List<Member> findAll();
}
