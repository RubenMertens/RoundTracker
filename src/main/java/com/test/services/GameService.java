package com.test.services;

import com.test.domain.Round;
import com.test.domain.RoundEntity;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ravanys on 21/03/2017.
 */
@Service
public class GameService {
    
    private Round round;

    public GameService() {
        this.round = new Round();
    }

    public void addRoundEntity(RoundEntity roundEntity){
        this.round.addRoundEntity(roundEntity);
    }


    public void sortRound(){
        this.round.sortRoundByInitiative();
    }

    public Round getRound(){
        return this.round;
    }


    public void nextTurn() {
        this.round.nextTurn();
    }
}
