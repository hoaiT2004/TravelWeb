package com.example.tourweb.controller.crud;


import com.example.tourweb.model.ChangePasswordRequest;
import com.example.tourweb.service.crud.PasswordService;
import com.example.tourweb.service.crud.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
public class PasswordController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordService passwordService;

    @GetMapping("/changePassword")
    public String changePassword(){
        return "changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(Model model, @ModelAttribute ChangePasswordRequest changePasswordRequest){
        String inform = passwordService.changePassword(changePasswordRequest);
        if(inform.equals("success")){
            return "redirect:/user/profile";
        }else{
            model.addAttribute("errorBE",inform);
            return "changePassword";
        }
    }

    @GetMapping("/getPassword")
    public String getPassword(){
        return "getPassword";
    }

    @PostMapping("/getPassword")
    public String getPassword(Model model, @RequestParam(name = "username") String username){
        var user = userService.findByUsername(username);
        if(user != null){
            return "redirect:/user/sendPasswordViaEmail?email="+user.getEmail()+"&username="+username;
        }else{
            model.addAttribute("errorBE","Tên đăng nhập không tồn tại!");
            return "getPassword";
        }
    }
}
