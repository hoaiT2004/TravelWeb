package com.example.tourweb.controller.email;


import com.example.tourweb.entity.User;
import com.example.tourweb.model.CodeRequest;
import com.example.tourweb.model.EmailRequest;
import com.example.tourweb.service.crud.PasswordService;
import com.example.tourweb.service.crud.UserService;
import com.example.tourweb.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class EmailController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/email")
    public String EnterNewEmail(Model model, Authentication authentication) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("money", user.getMoney());
        model.addAttribute("linkAvatar", user.getLinkAvatar());
        model.addAttribute("mainUsername", authentication.getName());

        return "email";
    }

    @GetMapping("/sendEmail")
    public String sendEmail(@ModelAttribute EmailRequest emailRequest,@RequestParam(name = "email")String email ,Model model, Authentication authentication){
        Random rd = new Random();
        String code="";
        for(int i = 0;i < 6;i++){
            code += rd.nextInt(10)+"";
        }
        if(emailRequest.getEmail() != null) email = emailRequest.getEmail();
        String content = "<p style='text-align:center;'>Xin chào "+ SecurityContextHolder.getContext().getAuthentication().getName()+",</p>" +
                "<p style='text-align:center;'>Mã xác minh tài khoản Shopee của bạn là:</p><br><h1 style='text-align:center;'>" + code + "</h1>"
                + "<p style='text-align:center;'>Có hiệu lực trong 2 phút. KHÔNG chia sẻ mã này với người khác, kể cả nhân viên Website.</p>";
        boolean isSent =  emailService.sendEmail(email,"Website: Mã OTP để xác minh tài khoản",content);
        if(isSent){
            LocalDateTime currentDateTime = LocalDateTime.now();
            model.addAttribute("email", email);
            model.addAttribute("time",currentDateTime);
            model.addAttribute("codeTrue",code);
            return "enterCode";
        }
        return "redirect:/user/email";
    }

    @PostMapping("/verify")
    public String verify(@ModelAttribute CodeRequest codeRequest,Model model){
        codeRequest.setEnterCodeDatetime(LocalDateTime.now());
        String error = emailService.verifyCode(codeRequest);
        if(!error.equals("success")) {
            model.addAttribute("error",error);
            model.addAttribute("codeTrue", codeRequest.getCodeTrue());
            return "enterCode";
        }
        return "redirect:/user/profile";
    }

    @Autowired
    private PasswordService passwordService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/sendPasswordViaEmail")
    public String sendPasswordViaEmail(@RequestParam(name = "email")String email,@RequestParam(name = "username")String username,Model model){
        Random rd = new Random();
        String code="";
        for(int i = 0;i < 6;i++){
            code += rd.nextInt(10)+"";
        }
        String content = "<p style='text-align:center;'>Xin chào "+ username+",</p>" +
                "<p style='text-align:center;'>Mật khẩu mới của bạn là:</p><br><h1 style='text-align:center;'>" + code;
        boolean isSent = emailService.sendEmail(email,"Website: Mật khẩu mới",content);
        if(isSent){
            System.out.println("Success");
        }
        passwordService.updatePassword(username,passwordEncoder.encode(code));
        model.addAttribute("email",email);
        model.addAttribute("username",username);
        return "NaN";
    }
}
