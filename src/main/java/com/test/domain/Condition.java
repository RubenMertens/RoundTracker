package com.test.domain;

/**
 * Ruben Mertens
 * 06/04/2017
 */
public class Condition {
    private int numberOfTurns;
    private String description;

    public Condition(int numberOfTurns, String description) {
        this.numberOfTurns = numberOfTurns;
        this.description = description;
    }

    public void decreaseNumberOfTurns(){
        this.numberOfTurns--;
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
