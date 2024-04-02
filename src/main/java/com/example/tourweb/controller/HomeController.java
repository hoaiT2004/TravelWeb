package com.example.tourweb.controller;

import com.example.tourweb.entity.Product;
import com.example.tourweb.entity.User;
import com.example.tourweb.service.crud.UserService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.tourweb.service.admin.ProductService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/home")
    public String home(Authentication authentication, Model model, @Nullable @RequestParam(name = "option")String option,@Nullable @RequestParam(name = "searchValue") String searchValue){
        if(authentication != null) {
            UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User user = userService.findByUsername(userDetails.getUsername());
            if (user != null) {
                model.addAttribute("username", user.getUsername());
                model.addAttribute("money", user.getMoney());
                model.addAttribute("role", user.getRoles());
            }
        }
        if(option != null && searchValue != null){
            model.addAttribute("products",model.getAttribute("products"));
        }else {
            List<Product> products = productService.getAllProduct();
            model.addAttribute("products", products);
        }
        return "index";
    }
}
