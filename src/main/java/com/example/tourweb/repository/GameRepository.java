package com.example.tourweb.repository;

import com.example.tourweb.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public interface GameRepository extends JpaRepository<Game,String> {

    List<Game> findGameByCategory(String category);

    @Transactional
    @Modifying
    @Query("UPDATE Game g SET g.category = :category,g.ansA = :ansA,g.ansB = :ansB,g.ansC = :ansC,g.ansD = :ansD,g.receiveMoney = :receiveMoney,g.answerRight = :answerRight WHERE g.question = :question")
    void updateGame(String category,String ansA,String ansB,String ansC,String ansD,String answerRight,int receiveMoney,String question);
}
