package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    // 인증번호 요청
    @GetMapping("/code")
    public boolean authCode(String email){
        System.out.println("AuthController.authCode");
        return authService.authCode(email);
    }

    @PostMapping("/check")
    public boolean authCheck(String authCode){
        return authService.authCheck(authCode);
    }
}
