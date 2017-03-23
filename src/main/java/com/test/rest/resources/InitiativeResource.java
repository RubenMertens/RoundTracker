package com.test.rest.resources;

/**
 * Created by Ravanys on 21/03/2017.
 */
public class InitiativeResource {
    private String playerId;
    private int initiative;

    public InitiativeResource() {
    }

    public InitiativeResource(String playerId, int initiative) {
        this.playerId = playerId;
        this.initiative = initiative;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
}
