package ex.wookis.mvc2.controller;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.exception.MemberException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiMemberController {

    /**
     * Exception Handler 테스트용 컨트롤러
     */

    @GetMapping("/{memberId}")
    public MemberDto getMember(@PathVariable("memberId") String memberId) {

        if (memberId.equals("admin")) {
            throw new MemberException("잘못된 사용자 입니다.");
        }

        return new MemberDto(memberId,"Hello Spring");
    }

    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

}
