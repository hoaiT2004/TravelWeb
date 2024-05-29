package com.example.tourweb.service.file;

import com.cloudinary.Cloudinary;
import com.example.tourweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FileService {

    @Autowired
    private Cloudinary cloudinary;


    @Autowired
    private UserRepository userRepository;

    public void updateLinkAvatar(String linkAvatar,String username){
        userRepository.updateLinkAvatar(linkAvatar,username);
    }

    public String upload(MultipartFile file)  {
        try{
            Map data = this.cloudinary.uploader().upload(file.getBytes(), Map.of());
            String url = (String) data.getOrDefault("url", null);
            return url;
        }catch (IOException io){
            throw new RuntimeException("Image upload fail");
        }
    }
}
