package ex.wookis.mvc2.repository.jpa;

import ex.wookis.mvc2.domain.LoginHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SDJLoginHistoryRepository extends JpaRepository<LoginHistory, Long> {

}
