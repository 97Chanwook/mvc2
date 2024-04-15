package ex.wookis.mvc2.repository.jpa;

import ex.wookis.mvc2.domain.LoginHistory;
import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Slf4j
class CustomJpaMemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void findLoginId() {
        String loginId = "cksdnr0710";
        String password = "kcw970710*";

        Member member = memberRepository.findByUserLoginId(loginId)
                .orElse(null);

        if (member != null) {
            LocalDateTime now = LocalDateTime.now();
            LoginHistory loginHistory = new LoginHistory(member, now);
            log.info("Login Hisotory = {}", loginHistory);
        }

        Assertions.assertThat(member.getName()).isEqualTo("김찬욱");
    }

    @Test
    void findAll() {
        for (Member member : memberRepository.findAll()) {
            log.info("Member is {}",member);
        }

    }


}