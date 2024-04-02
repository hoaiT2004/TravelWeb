package com.example.tourweb.service.file;

import com.example.tourweb.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class FileService {

    @Autowired
    private UserRepository userRepository;

    public void updateLinkAvatar(String linkAvatar,String username){
        userRepository.updateLinkAvatar(linkAvatar,username);
    }
    public void uploadLinkAvatar(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        Path imagesDirectory = Paths.get("src", "main", "resources", "static", "images", "avatar");
        File imagesFolder = imagesDirectory.toFile();
        File targetFile = new File(imagesFolder, fileName);
        System.out.println("file:"+targetFile.toPath()+"\t"+imagesFolder.toPath());
        if (targetFile.exists()) {
            return;
        }
        try {
            Files.copy(file.getInputStream(), targetFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String addImageUrlProduct(MultipartFile file){
        String fileName = file.getOriginalFilename();
        Path imagesDirectory = Paths.get("src", "main", "resources", "static", "images");
        File imagesFolder = imagesDirectory.toFile();
        File targetFile = new File(imagesFolder,fileName);
        if (targetFile.exists()) {
            return "File with the same name already exists: " + fileName;
        }
        try {
            Files.copy(file.getInputStream(), targetFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "success";
    }

    public String updateImageUrlProduct(MultipartFile file,String imageUrl){
        String fileName = file.getOriginalFilename();
        if(fileName.equals(imageUrl)) {
            return "same";
        }
            Path imagesDirectory = Paths.get("src", "main", "resources", "static", "images");
            File imagesFolder = imagesDirectory.toFile();
            File targetFile = new File(imagesFolder,fileName);
            File oldTargetFile = new File(imagesFolder,imageUrl);
            try {
                Files.delete(oldTargetFile.toPath());
                Files.copy(file.getInputStream(), targetFile.toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        return "success";
    }
}
