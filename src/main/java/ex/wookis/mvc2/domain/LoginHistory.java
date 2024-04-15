package ex.wookis.mvc2.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "login_hisotry")
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "login_id")
    private Member member;

    @Column(name = "login_time")
    private LocalDateTime loginTime;

    public LoginHistory() {
    }

    public LoginHistory(Member member, LocalDateTime loginTime) {
        this.member = member;
        this.loginTime = loginTime;
    }
}
