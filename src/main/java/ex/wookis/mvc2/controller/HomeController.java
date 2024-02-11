package ex.wookis.mvc2.controller;

import ex.wookis.mvc2.domain.Member;
import ex.wookis.mvc2.domain.SessionConst;
import ex.wookis.mvc2.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService service;

    @GetMapping("/")
    public String home(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member member,
                       Model model) {
        if (member == null) {
            return "home";
        }
        model.addAttribute("member",member);
        return "loginHome";
    }


    @GetMapping("/download/{itemId}")
    public ResponseEntity<Resource> download(@PathVariable("itemId") Long itemId) throws MalformedURLException {
        return service.download(itemId);
    }

    @ResponseBody   //*** ResponseBody 꼭 필요
    @GetMapping("/img/{fileName}")
    public Resource image(@PathVariable("fileName") String fileName) throws MalformedURLException {
        return service.image(fileName);
    }
}
