package ex.wookis.mvc2;

import ex.wookis.mvc2.domain.Item;
import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.repository.ItemRepository;
import ex.wookis.mvc2.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        Member member = new Member();
        member.setLoginId("test");
        member.setPassword("test!");
        member.setName("테스터");

        memberRepository.save(member);
    }
}
