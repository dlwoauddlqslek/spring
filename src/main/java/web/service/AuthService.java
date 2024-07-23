package web.service;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    HttpServletRequest request;

    public boolean authCode(String email){
        System.out.println("AuthService.authCode");
        try {
            String authCode = "";

            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                authCode += random.nextInt(10);
            }
            System.out.println("authCode = " + authCode);

            request.getSession().setAttribute("authCode", authCode);
            request.getSession().setMaxInactiveInterval(180);
            //emailSend(email,"인증코드","인증코드"+authCode);
            return true;
        }catch (Exception e){System.out.println(e);}
        return false;
    }

    public boolean authCheck(String authCodeInput){
        Object object=request.getSession().getAttribute("authCode");
        if (object!=null){
            String authCode=(String) object;
            if (authCode.equals(authCodeInput)){
                return true;
            }
        }
        return false;
    }

    @Autowired
    JavaMailSender javaMailSender;
    public void emailSend(String toEmail,String subject,String content){
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            mimeMessageHelper.setFrom("ljm5857@naver.com");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(content);
            javaMailSender.send(mimeMessage);
        }catch (Exception e){System.out.println(e);}
    }
}
