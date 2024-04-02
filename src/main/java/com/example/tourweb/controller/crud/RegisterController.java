package com.example.tourweb.controller.crud;

import com.example.tourweb.model.RegisterRequest;
import com.example.tourweb.model.UserDto;
import com.example.tourweb.service.crud.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest registerRequest){
        UserDto userDto = registerService.register(registerRequest);
        if(userDto != null){
            return "redirect:/user/login";
        }
        return "redirect:/user/register";
    }
}
