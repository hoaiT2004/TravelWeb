package com.example.tourweb.controller.crud;

import com.example.tourweb.model.ChangeInfoRequest;
import com.example.tourweb.service.crud.InfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("user")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @PostMapping("/changeInfo")
    public String changeInfo(@ModelAttribute ChangeInfoRequest changeInfoRequest){
        infoService.changeInfo(changeInfoRequest);
        return "redirect:/user/profile";
    }
}
