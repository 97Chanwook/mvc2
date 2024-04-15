package ex.wookis.mvc2.service;

import ex.wookis.mvc2.domain.LoginHistory;
import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.LoginHistoryRepository;
import ex.wookis.mvc2.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;
    private final LoginHistoryRepository loginHistoryRepository;

    /**
     * 
     * @param loginId
     * @param password
     * @return null 이면 실패
     */
    public Member login(String loginId, String password) {

        Member member = memberRepository.findByUserLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);

        if (member != null) {
            LocalDateTime now = LocalDateTime.now();
            loginHistoryRepository.save(new LoginHistory(member, now));
        }

        return member;
    }

    public void join(Member member) {
        memberRepository.save(member);
    }
}
