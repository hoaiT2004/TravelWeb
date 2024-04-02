package com.example.tourweb.controller.admin;

import com.example.tourweb.entity.Bill;
import com.example.tourweb.service.admin.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    //manage order
    @GetMapping("/manage")
    public String manageOrder(Model model){
        List<Bill> order = orderService.getAllBill();
        model.addAttribute("Orders",order);
        model.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        return "order/manageOrder";
    }
}
