package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.model.dto.MemberDto;
import web.service.MemberService;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired MemberService memberService;

    // POST http://localhost:8080/member/signup?id=qwe&pw=asd&name=강호동&email=qwe@qwe&phone=123-123-123
    @PostMapping("/signup")
    //public boolean mSignup( String id , String pw , String name , String email , String phone ){
    public boolean mSignup( MemberDto memberDto ){
        System.out.println("MemberController.mSignup");
        System.out.println("memberDto = " + memberDto);
        return memberService.mSignup( memberDto );
    }

    // http://localhost:8080/member/login?id=qwe&pw=asd
    @PostMapping("/login")
    public boolean mLogin( MemberDto memberDto ){
        System.out.println("MemberController.mLogin");
        System.out.println("memberDto = " + memberDto);
        return memberService.mLogin( memberDto );
    }

    // 로그인 체크
    @GetMapping("/login/check")
    public MemberDto mLoginCheck( ){
        return memberService.mLoginCheck();
    }

    // 로그아웃
    @GetMapping("/logout")
    public void mLogout(){
        memberService.mLogout();
    }

    // 마이페이지 정보
    @GetMapping("/my/info")
    public MemberDto mMyInfo( ){ return memberService.mMyInfo(); }

    // id중복검사
    @GetMapping("/signup/idcheck")
    public boolean idCheck(String id){
        System.out.println("MemberController.idCheck");
        return memberService.idCheck(id);
    }

}










