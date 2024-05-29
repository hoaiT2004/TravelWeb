package com.example.tourweb.service.admin;


import com.example.tourweb.entity.Product;
import com.example.tourweb.model.ProductRequest;
import com.example.tourweb.repository.ProductRepository;
import com.example.tourweb.service.file.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FileService fileService;

    public List<Product> getAllProduct(){
        return productRepository.findAll();
    }

    public Product findProductByName(String name){
        return productRepository.findById(name).orElse(null);
    }

    public void updateProduct(ProductRequest productRequest,MultipartFile file){
        String url = this.fileService.upload(file);
        productRepository.updateProduct(productRequest.getDescription(),url , productRequest.getPrice(), productRequest.getName());
    }

    public void deleteProduct(String name){
        productRepository.deleteById(name);
    }

    public String addProduct(ProductRequest productRequest, MultipartFile file){
        var product = productRepository.findById(productRequest.getName().toLowerCase()).orElse(null);
        if(product != null){
            return "Sản phẩm đã tồn tại!";
        }
        String url = this.fileService.upload(file);
        var addProduct = Product.builder()
                        .name(productRequest.getName())
                        .description(productRequest.getDescription())
                        .imageUrl(url)
                        .price(productRequest.getPrice())
                        .build();
        productRepository.save(addProduct);
        return "success";
    }
}
