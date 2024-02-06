package ex.wookis.mvc2.domain;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 회원 관리 Model
 */
@Data
public class Member {

    private Long id;

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
