package com.example.tourweb.controller.game;


import com.example.tourweb.model.AnswerFormRequest;
import com.example.tourweb.model.GameRequest;
import com.example.tourweb.service.crud.UserService;
import com.example.tourweb.service.game.GameForUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user/game")
public class GameForUserController {

    @Autowired
    private GameForUserService gameForUserService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String interfaceGame(Model model){
        model.addAttribute("game",gameForUserService.getAllGame());
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "game/earnMoney";
    }

    @GetMapping("/math.")
    public String interfaceMath(Model model){
        String category = "math";
        implement(category,model);
        return "game/math";
    }

    @GetMapping("/chemistry")
    public String interfaceChemistry(Model model){
        String category = "chemistry";
        implement(category,model);
        return "game/chemistry";
    }

    @GetMapping("/english")
    public String interfaceEnglish(Model model){
        String category = "english";
        implement(category,model);
        return "game/english";
    }

    public void implement(String category,Model model){
        model.addAttribute("game",gameForUserService.getAllGameByCategory(category));
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @PostMapping("/checkResults")
    public String checkResult(RedirectAttributes redirectAttributes, @ModelAttribute AnswerFormRequest answerRequestList){
        int receiveMoney = 0,trueQuestionNumber = 0;
        for (GameRequest gameRequest :answerRequestList.getGameRequests()) {
            if(gameRequest.getAnswerRight().equals(gameRequest.getAnswer())){
                receiveMoney+= gameRequest.getReceiveMoney();
                trueQuestionNumber++;
            }
        }
        redirectAttributes.addFlashAttribute("totalQuestion",answerRequestList.getGameRequests().size());
        redirectAttributes.addFlashAttribute("trueQuestionNumber",trueQuestionNumber);
        var user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        userService.updateMoneyUser(receiveMoney+ user.getMoney(), user.getUsername());
        redirectAttributes.addFlashAttribute("money",receiveMoney+ user.getMoney());
        return "redirect:/user/game/inform";
    }
    @GetMapping("/inform")
    public String inform(Model model){
        model.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("trueQuestionNumber",model.getAttribute("trueQuestionNumber")+"");
        model.addAttribute("totalQuestion",model.getAttribute("totalQuestion")+"");
        model.addAttribute("money",model.getAttribute("money"));
        return "game/inform";
    }
}
