package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;


@RestController
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/member/signup")
    public boolean signUp(MemberDto memberDto){
        System.out.println("signup");
        System.out.println("memberDto = " + memberDto);
        return memberService.signUp(memberDto);
    }

    @PostMapping("/member/login")
    public void logIn(){
        System.out.println("MemberController.logIn");
    }
}
