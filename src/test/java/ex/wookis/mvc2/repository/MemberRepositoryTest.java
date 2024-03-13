package ex.wookis.mvc2.repository;

import ex.wookis.mvc2.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void save() {
        Member member = new Member();
        member.setName("kcw");
        member.setLoginId("kcw0710");
        member.setPassword("asd123!");

        Member saveMember = memberRepository.save(member);
        System.out.println("saveMember = " + saveMember);


        assertThat(saveMember.getName()).isEqualTo(member.getName());
    }
}