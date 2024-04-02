package com.example.tourweb.controller.crud;

import com.example.tourweb.entity.User;
import com.example.tourweb.service.crud.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String profile(Authentication authentication, Model model){
        String username = "";
        if(model.containsAttribute("username")) username = model.getAttribute("username")+"";
        User user;
        if(!username.isEmpty()) user = userService.findByUsername(username);
        else user = userService.findByUsername(authentication.getName());
        if (user != null) {
            String email = user.getEmail();
            String emailUser = "";
            for (int i = 0; i < email.length(); i++) {
                if(email.charAt(i) != '@'){
                    if(i > 2) emailUser += "*";
                    else emailUser += email.charAt(i)+"";
                }else if(email.charAt(i) == '@'){
                    emailUser += "@gmail.com";
                    break;
                }
            }
            String tel = user.getTel();
            String telUser = "";
            for (int i = 0; i < tel.length(); i++) {
                if(i > tel.length() - 2 || i <= 2){
                    telUser += tel.charAt(i) + "";
                }else telUser += "*";
            }
            model.addAttribute("email",emailUser);
            model.addAttribute("fullName",user.getFullName());
            model.addAttribute("tel",telUser);
            model.addAttribute("gender", user.getGender());
            model.addAttribute("dateOfBirth", user.getDateOfBirth());
            model.addAttribute("country", user.getCountry());
            model.addAttribute("mainUsername", authentication.getName());
            model.addAttribute("username", user.getUsername());
            model.addAttribute("money", user.getMoney());
            model.addAttribute("linkAvatar", user.getLinkAvatar());
        }
        return "profile";
    }
}
