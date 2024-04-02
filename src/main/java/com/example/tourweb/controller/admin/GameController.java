package com.example.tourweb.controller.admin;

import com.example.tourweb.entity.Game;
import com.example.tourweb.model.GameRequest;
import com.example.tourweb.service.admin.GameService;
import com.example.tourweb.service.encode_decode.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("admin/game")
public class GameController {

    public final String secretKey = "hoadev";

    @Autowired
    private GameService gameService;

    @Autowired
    private Util util;

    //Show game
    @GetMapping("/manage")
    public String manageGame(Model model){
        List<Game> gameList = gameService.getAllGame();
        model.addAttribute("game",gameList);
        model.addAttribute("username", SecurityContextHolder.getContext().getAuthentication().getName());
        return "game/manageGame";
    }

    //create game
    @GetMapping("/add")
    public String addGame(Model model){
        if(model.getAttribute("errorBE") != null) model.addAttribute("errorBE",model.getAttribute("errorBE")+"");
        model.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        return "game/addGame";
    }

    @PostMapping("/add")
    public String addGame(@ModelAttribute GameRequest gameRequest, RedirectAttributes attributes){
        gameRequest.setEncodeQuestion(util.encrypt(gameRequest.getQuestion(),secretKey));
        String error = gameService.addGame(gameRequest);
        if(!error.equals("success")){
            attributes.addFlashAttribute("errorBE",error);
            return "redirect:/admin/game/add";
        }
        return "redirect:/admin/game/manage";
    }

    //edit game
    @GetMapping("/edit")
    public String editGame(Model model,@RequestParam(name = "question") String encodeQuestion){
        String decodedQuestion = util.decrypt(encodeQuestion,secretKey);
        Game game = gameService.findGameByQuestion(decodedQuestion);
        model.addAttribute("game",game);
        model.addAttribute("username",SecurityContextHolder.getContext().getAuthentication().getName());
        return "game/editGame";
    }

    @PostMapping("/edit")
    public String editGame(@ModelAttribute GameRequest gameRequest,
                           @RequestParam(name = "categoryOld") String category,@RequestParam(name = "ansAOld") String ansA
            ,@RequestParam(name = "ansBOld") String ansB,@RequestParam(name = "ansCOld") String ansC
            ,@RequestParam(name = "ansDOld") String ansD,@RequestParam(name = "answerRightOld") String answerRight){
        if(gameRequest.getCategory().equals(category) && gameRequest.getAnsA().equals(ansA)&&
                gameRequest.getAnsB().equals(ansB) && gameRequest.getAnsC().equals(ansC)&&
                gameRequest.getAnsD().equals(ansD) && gameRequest.getAnswerRight().equals(answerRight)){
            System.out.println("Same");
        }else{
            gameService.editGame(gameRequest);
        }
        return "redirect:/admin/game/manage";
    }

    //delete game
    @GetMapping("/delete")
    public String deleteGame(@RequestParam(name = "question") String encodeQuestion){
        String decodedQuestion = util.decrypt(encodeQuestion,secretKey);
        gameService.deleteGame(decodedQuestion);
        return "redirect:/admin/game/manage";
    }
}
