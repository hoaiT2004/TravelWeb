package com.example.tourweb.service.game;


import com.example.tourweb.entity.Game;
import com.example.tourweb.model.AnswerFormRequest;
import com.example.tourweb.model.GameRequest;
import com.example.tourweb.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameForUserService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getAllGame(){
        return gameRepository.findAll();
    }

    public AnswerFormRequest getAllGameByCategory(String category) {
        List<Game> gameList = gameRepository.findGameByCategory(category);
        AnswerFormRequest answerFormRequest = new AnswerFormRequest();
        List<GameRequest> gameRequests = new ArrayList<>();
        for (Game game:gameList
             ) {
            gameRequests.add(GameRequest.builder()
                            .question(game.getQuestion())
                            .category(game.getCategory())
                            .ansA(game.getAnsA())
                            .ansB(game.getAnsB())
                            .ansC(game.getAnsC())
                            .ansD(game.getAnsD())
                            .answerRight(game.getAnswerRight())
                            .receiveMoney(game.getReceiveMoney())
                    .build());
        }
        answerFormRequest.setGameRequests(gameRequests);
        return answerFormRequest;
    }
}
