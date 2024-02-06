package ex.wookis.mvc2.service;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository repository;

    /**
     * 
     * @param loginId
     * @param password
     * @return null 이면 실패
     */
    public Member login(String loginId, String password) {
        return repository.findByUserLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }

    public void join(Member member) {
        repository.save(member);
    }
}
