package com.example.tourweb.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CloudinaryConfig {
    @Bean
    public Cloudinary getCloudinary(){
        Map config = new HashMap();
        config.put("cloud_name", "hoaptit");
        config.put("api_key", "285189351798117");
        config.put("api_secret", "HS1lExolN67Zp8nSoz6iViGoAAI");
        config.put("secure", true);
        return new Cloudinary(config);
    }

}
