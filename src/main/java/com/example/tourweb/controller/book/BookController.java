package com.example.tourweb.controller.book;

import com.example.tourweb.entity.Bill;
import com.example.tourweb.model.BillRequest;
import com.example.tourweb.service.book.BookService;
import com.example.tourweb.service.crud.UserService;
import com.example.tourweb.service.product.ProductService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    private ProductService2 productService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping
    public String interFace(Model model, @RequestParam(name = "where") String whereTo,@RequestParam(name = "money") int moneyUser){
        var productRequest = productService.getProductByName(whereTo);
        var user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("role", user.getRoles());
        model.addAttribute("moneyWhereTo",productRequest.getPrice());
        model.addAttribute("whereTo",whereTo);
        String error = model.getAttribute("error")+"";
        if(!error.isEmpty()) model.addAttribute("error",error);
        model.addAttribute("moneyUser",moneyUser);
        model.addAttribute("totalMoney","Total cost per person: " + productRequest.getPrice() +"$");
        return "/order/book";
    }

    @PostMapping
    public String resolveBook(@RequestParam(name="moneyWhereTo") int     moneyWhereTo,@RequestParam(name="moneyUser") int moneyUser,@ModelAttribute BillRequest orderRequest, RedirectAttributes redirectAttributes){
        orderRequest.setTotalMoney(orderRequest.getGuests() * moneyWhereTo);
        String inform = bookService.resolveBook(orderRequest);
        if(!inform.equals("success")){
            redirectAttributes.addFlashAttribute("error",inform);
            return "redirect:/api/book?where="+ orderRequest.getWhereTo()+"&money="+moneyUser;
        }
        return "redirect:/api/book/show";
    }

    @GetMapping("/show")
    public String showBill(Model model){
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        List<Bill> bills = bookService.getAllBillByUser_Username(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("bills",bills);
        return "/order/showBill";
    }
}
