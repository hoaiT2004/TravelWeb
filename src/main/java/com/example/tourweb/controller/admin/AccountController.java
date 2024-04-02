package com.example.tourweb.controller.admin;

import com.example.tourweb.entity.User;
import com.example.tourweb.service.admin.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("admin/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    //manage account
    @GetMapping("/manage")
    public String manageAccount(Model model) {
        List<User> account = accountService.getAllAccount();
        model.addAttribute("Accounts", account);
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "account/manageAccount";
    }

    //show account
    @GetMapping("/show")
    public String showAccount(@RequestParam(name = "username")String username, RedirectAttributes attributes){
        attributes.addFlashAttribute("username",username);
        return "redirect:/user/profile";
    }
}
