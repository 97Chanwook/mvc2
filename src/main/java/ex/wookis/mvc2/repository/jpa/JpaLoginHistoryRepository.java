package ex.wookis.mvc2.repository.jpa;

import ex.wookis.mvc2.domain.LoginHistory;
import ex.wookis.mvc2.repository.LoginHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class JpaLoginHistoryRepository implements LoginHistoryRepository {

    private final SDJLoginHistoryRepository repository;

    @Transactional(propagation = Propagation.REQUIRES_NEW) //REQUIRES_NEW -> 기존 트랜잭션에 참여하지 않고 새로운 트랜잭션 메니저를 생성하여 별도 처리를 함.
    public LoginHistory save(LoginHistory history){
        repository.save(history);
        return history;
    }

}
