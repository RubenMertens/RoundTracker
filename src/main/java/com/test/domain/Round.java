package com.test.domain;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Ravanys on 05/04/2017.
 */
public class Round {
    private LinkedList<RoundEntity>  roundList;
    private int roundCounter;
    private RoundEntity roundFirstEntity;
    private LinkedList<RoundEntity> buffer;

    public Round() {
        roundList = new LinkedList<>();
        buffer = new LinkedList<>();
        this.roundCounter = 0;
    }

    public void addRoundEntity(RoundEntity roundEntity){

        if(roundList.contains(roundEntity)){
            roundList.set(roundList.indexOf(roundEntity),roundEntity);
        }else{
            roundList.add(roundEntity);
        }
    }

    public void addRoundEntityToBuffer(RoundEntity roundEntity){
        buffer.add(roundEntity);
    }

    public void sortRoundByInitiative(){
        roundList.sort((e1,e2) -> {
            if(e1.getFullInitiative() == e2.getFullInitiative()){
                return e2.getInitiativeModifier() - e1.getInitiativeModifier();
            }
            return e2.getFullInitiative() - e1.getFullInitiative();
        });
        this.roundFirstEntity = roundList.getFirst();
    }

    public LinkedList<RoundEntity> getRoundList() {
        return roundList;
    }

    public RoundEntity getCurrentRoundEntity(){
        return roundList.getFirst();
    }

    public void nextTurn(){
        RoundEntity first = roundList.getFirst();
        roundList.removeFirst();
        roundList.addLast(first);
        if(roundList.getFirst().equals(this.roundFirstEntity)){
            roundCounter++;
            mergeBufferToRoundList();
        }
    }

    private void mergeBufferToRoundList(){
        if(buffer.size() > 0){
            for (RoundEntity roundEntity : buffer) {
                addRoundEntity(roundEntity);
            }
            buffer = new LinkedList<>();
            sortRoundByInitiative();
        }

    }

    public int getRoundCounter() {
        return roundCounter;
    }
}
