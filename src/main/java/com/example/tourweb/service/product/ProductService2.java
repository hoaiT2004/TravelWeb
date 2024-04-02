package com.example.tourweb.service.product;

import com.example.tourweb.model.BillRequest;
import com.example.tourweb.model.ProductRequest;
import com.example.tourweb.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService2 {

    @Autowired
    private ProductRepository productRepository;

    public ProductRequest getProductByName(String name){
        var product = productRepository.findById(name).orElse(null);
        return ProductRequest.builder()
                .name(name)
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }
}

