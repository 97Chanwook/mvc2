package ex.wookis.mvc2.repository.jpa;

import ex.wookis.mvc2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String userId);
}
