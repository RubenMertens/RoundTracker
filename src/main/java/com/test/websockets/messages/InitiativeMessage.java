package com.test.websockets.messages;

/**
 * Created by Ravanys on 21/03/2017.
 */
public class InitiativeMessage {

    private String playerId;
    private Integer initiative;

    public InitiativeMessage() {
    }

    public InitiativeMessage(String playerId, Integer initiative) {
        this.playerId = playerId;
        this.initiative = initiative;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getInitiative() {
        return initiative;
    }

    public void setInitiative(Integer initiative) {
        this.initiative = initiative;
    }
}
