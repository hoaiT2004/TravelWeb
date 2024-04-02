package com.example.tourweb.service.admin;

import com.example.tourweb.entity.Game;
import com.example.tourweb.model.GameRequest;
import com.example.tourweb.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGame(){
        return gameRepository.findAll();
    }

    public Game findGameByQuestion(String question){
        return gameRepository.findById(question).orElse(null);
    }

    public String addGame(GameRequest gameRequest){
        var checkExistGame = gameRepository.findById(gameRequest.getQuestion()).orElse(null);
        if(checkExistGame != null){
            return "Câu hỏi đã tồn tại!";
        }
        var game = Game.builder()
                .question(gameRequest.getQuestion())
                .encodeQuestion(gameRequest.getEncodeQuestion())
                .category(gameRequest.getCategory())
                .ansA(gameRequest.getAnsA())
                .ansB(gameRequest.getAnsB())
                .ansC(gameRequest.getAnsC())
                .ansD(gameRequest.getAnsD())
                .receiveMoney(gameRequest.getReceiveMoney())
                .answerRight(gameRequest.getAnswerRight())
                .build();
        gameRepository.save(game);
        return "success";
    }
    public void editGame(GameRequest gameRequest) {
        gameRepository.updateGame(gameRequest.getCategory(), gameRequest.getAnsA(), gameRequest.getAnsB(), gameRequest.getAnsC(), gameRequest.getAnsD(), gameRequest.getAnswerRight(), gameRequest.getReceiveMoney() ,gameRequest.getQuestion());
    }

    public void deleteGame(String question){
        gameRepository.deleteById(question);
    }
}
