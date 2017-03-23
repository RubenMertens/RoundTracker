package com.test.websockets.messages;

import java.util.Map;

/**
 * Created by Ravanys on 21/03/2017.
 */
public class InitiativeListMessage {

    private Map<String,Integer> initiativeMap;

    public InitiativeListMessage(Map<String, Integer> initiativeMap) {
        this.initiativeMap = initiativeMap;
    }

    public InitiativeListMessage() {
    }

    public Map<String, Integer> getInitiativeMap() {
        return initiativeMap;
    }

    public void setInitiativeMap(Map<String, Integer> initiativeMap) {
        this.initiativeMap = initiativeMap;
    }
}
