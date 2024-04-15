package ex.wookis.mvc2.repository;

import ex.wookis.mvc2.domain.LoginHistory;

public interface LoginHistoryRepository {

    LoginHistory save(LoginHistory history);
}
