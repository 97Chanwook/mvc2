package ex.wookis.mvc2.controller;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.domain.SessionConst;
import ex.wookis.mvc2.form.LoginForm;
import ex.wookis.mvc2.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;
    
    //1. 로그인 페이지
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form) {
        return "loginForm";
    }

    //2. 로그인 기능 구현
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        @RequestParam(defaultValue = "/", name = "redirectURL") String redirectURL) {
        if (bindingResult.hasErrors()) {
            return "loginForm";
        }

        Member loginMember = service.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail","아이디 또는 패스워드가 일치하지 않습니다.");
            return "loginForm";
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:"+redirectURL;
    }

    //3. 회원가입 페이지
    @GetMapping("/join")
    public String addForm(@ModelAttribute("member") Member member) {
        return "join";
    }

    //4. 회원 가입 기능 구현
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute("member") Member member,
                       BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "join";
        }

        service.join(member);
        return "redirect:/";
    }

    //5. 로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
        return "redirect:/";
    }
}
