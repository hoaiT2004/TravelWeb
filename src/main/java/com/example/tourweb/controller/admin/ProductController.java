package com.example.tourweb.controller.admin;

import com.example.tourweb.entity.Product;
import com.example.tourweb.model.ProductRequest;
import com.example.tourweb.service.admin.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    //add product
    @GetMapping("/add")
    public String addProduct(Model model){
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "product/addProduct";
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute ProductRequest productRequest,@RequestParam(name = "imageUrl") MultipartFile file, Model model){
        System.out.println(productRequest);
        String error = productService.addProduct(productRequest,file);
        if(error.equals("success")){
            return "redirect:/api/home";
        }
        model.addAttribute("errorBE",error);
        return "product/addProduct";
    }

    //manage product
    @GetMapping("/manage")
    public String manageProduct(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "product/manageProduct";
    }

    //edit product
    @GetMapping("/edit")
    public String editProduct(@RequestParam(name = "name")String name, Model model){
        Product product = productService.findProductByName(name);
        model.addAttribute("name",name);
        model.addAttribute("price", product.getPrice());
        model.addAttribute("description", product.getDescription());
        product.setImageUrl(product.getImageUrl().substring(8));
        model.addAttribute("imageUrl", product.getImageUrl());
        return "product/editProduct";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute ProductRequest productRequest, @RequestParam(name = "imageUrl") MultipartFile file
            , @RequestParam(name = "oldPrice") double price
            , @RequestParam(name = "oldDescription") String description, @RequestParam(name = "oldImageUrl") String imageUrl){
        System.out.println(productRequest);
        if(price == productRequest.getPrice()
                && description.equalsIgnoreCase(productRequest.getDescription()) && imageUrl.equalsIgnoreCase(file.getOriginalFilename())) {
        }else
            productService.updateProduct(productRequest,file,imageUrl);
        return "redirect:/admin/product/manage";
    }

    //delete product
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam(name = "name")String name){
        productService.deleteProduct(name);
        return "redirect:/admin/product/manage";
    }
}
