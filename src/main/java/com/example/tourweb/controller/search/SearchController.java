package com.example.tourweb.controller.search;


import com.example.tourweb.entity.Product;
import com.example.tourweb.model.SearchRequest;
import com.example.tourweb.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("api/search")
public class SearchController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public String search(@ModelAttribute SearchRequest searchRequest, RedirectAttributes attributes){
        List<Product> products = new ArrayList<>();
        System.out.println(searchRequest);
        if(searchRequest.getOption().equals("productName")) {
            products = searchByProductName(searchRequest.getSearchValue());
        }else {
            products = searchByPrice(Integer.parseInt(searchRequest.getSearchValue()));
        }
        attributes.addFlashAttribute("products",products);
        return "redirect:/api/home?option="+ searchRequest.getOption()+"&searchValue="+ searchRequest.getSearchValue();
    }

    public List<Product> searchByProductName(String productName){
        return productRepository.findProductByNameLike("%"+productName+"%");
    }

    public List<Product> searchByPrice(int price){
        return productRepository.findProductByPriceIsGreaterThanEqual(price);
    }
}
