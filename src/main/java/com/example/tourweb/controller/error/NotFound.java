package com.example.tourweb.controller.error;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("api")
public class NotFound {

    @GetMapping("/404")
    public String notFound(){
        return "404";
    }

}
