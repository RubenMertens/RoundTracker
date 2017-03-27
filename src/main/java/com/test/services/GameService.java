package com.test.services;

import com.test.domain.RoundEntity;
import com.test.rest.resources.InitiativeResource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Ravanys on 21/03/2017.
 */
@Service
public class GameService {
    
    private Map<String,Integer> initiativeMap;
    private Map<RoundEntity,Boolean> roundEntities;

    public GameService() {
        this.initiativeMap = new HashMap<>();
        this.roundEntities = new LinkedHashMap<>();
    }

    public void setOrAddInitiative(String playerId, int init){
        this.initiativeMap.put(playerId,init);
    }

    public Map<String,Integer> getSortedList(){
        List<Map.Entry<String,Integer>> entryList = new ArrayList<>(this.initiativeMap.entrySet());
        entryList.sort((e1,e2) -> e2.getValue() - e1.getValue());
        Map<String,Integer> returnMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entry : entryList) {
            returnMap.put(entry.getKey(),entry.getValue());
        }
        return returnMap;
    }
    
    public List<RoundEntity> getRoundSortedByInitiative(){ //todo no double sort!
       List<RoundEntity> sortedList =  new ArrayList<>(this.roundEntities.keySet());
               sortedList.sort((e1,e2) -> {
            if(e1.getFullInitiative() == e2.getFullInitiative()){
                return e1.getInitiativeModifier() - e2.getInitiativeModifier();
            }else{
                return e1.getFullInitiative() - e2.getFullInitiative();
            }
        });

        return sortedList;
    }
    
    public void sortInternalRound(){
        List<Map.Entry<RoundEntity,Boolean>> round = new ArrayList<>(this.roundEntities.entrySet());
        round.sort((e1,e2) -> {
            if(e1.getKey().getFullInitiative() == e2.getKey().getFullInitiative()){
                return e1.getKey().getInitiativeModifier() - e2.getKey().getInitiativeModifier();
            }else{
                return e1.getKey().getFullInitiative() - e2.getKey().getFullInitiative();
            }
        });
        this.roundEntities = new LinkedHashMap<>();
        for (Map.Entry<RoundEntity, Boolean> entry : round) {
            this.roundEntities.put(entry.getKey(),entry.getValue());
        }
        
    }

    public void addRoundEntity(RoundEntity roundEntity){
        this.roundEntities.put(roundEntity,false);
    }
    
    public void newRound(){
        if(!checkIfRoundFinished()){
            throw new RuntimeException("Round not finished!");
        }
        for (Map.Entry<RoundEntity, Boolean> entry : roundEntities.entrySet()) {
            entry.setValue(false);
        }
    }
    
    public boolean checkIfRoundFinished(){
        for (Map.Entry<RoundEntity, Boolean> entry : roundEntities.entrySet()) {
            if(!entry.getValue()){
                return false;
            }
        }
        return true;
    }
    
    
    
    
}
