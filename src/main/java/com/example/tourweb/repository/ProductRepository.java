package com.example.tourweb.repository;

import com.example.tourweb.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,String> {
    @Transactional
    @Modifying
    @Query("UPDATE Product u SET u.description = :description,u.imageUrl = :imageUrl,u.price = :price WHERE u.name = :name")
    void updateProduct(String description,String imageUrl,double price, String name);
    List<Product> findProductByNameLike(String productName);
    List<Product> findProductByPriceIsGreaterThanEqual(int price);
}
