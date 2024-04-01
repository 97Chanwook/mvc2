package ex.wookis.mvc2.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원 관리 Model
 */
@Data
@Entity
//@Table(name = "Member") 객체 명과 테이블 명이 같다면 생략해도 됀다.
public class Member {

    //@Id : pk
    //@GeneratedValue(strategy = GenerationType.IDENTITY) : DB에서 값을 증가시켜줌
    // ex) Mysql Auto Increment
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Column(name = "item_name", length = 10)컬럼 정의
    // 컬럼명과 필드 명이 같다면 생략해도 됀다.
    @NotEmpty
    @Column(name = "login_id", length = 20)
    private String loginId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String password;

    //JPA 는 기본 생성자를 필수로 생성해야 한다.
    public Member() {
    }

    public Member(String loginId, String name, String password) {
        this.loginId = loginId;
        this.name = name;
        this.password = password;
    }
}
