package com.example.tourweb.controller.file;


import com.example.tourweb.service.file.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam(name = "linkAvatar")MultipartFile file,@RequestParam(name = "oldLinkAvatar") String linkAvatar){
        if(!file.getOriginalFilename().equals(linkAvatar)) {
            String url = this.fileService.upload(file);
            fileService.updateLinkAvatar(url, SecurityContextHolder.getContext().getAuthentication().getName());
        }
        return "redirect:/user/profile";
    }
}

