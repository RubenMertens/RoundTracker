package com.test.websockets;

import com.test.domain.RoundEntity;
import com.test.services.GameService;
import com.test.websockets.messages.ConditionWrapper;
import com.test.websockets.messages.MessageType;
import com.test.websockets.messages.MessageWrapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static com.test.InitiativeApplication.GSON;

/**
 * Created by Ravanys on 21/03/2017.
 */

public class SessionHandler {


    private GameService gameService;

    private WebSocketSession session;
    private String id;
    private WebSocketHandler webSocketHandler;

    public SessionHandler(GameService gameService, WebSocketSession session, String id, WebSocketHandler webSocketHandler) {
        this.gameService = gameService;
        this.session = session;
        this.id = id;
        this.webSocketHandler = webSocketHandler;
    }

    public void handleMessage(MessageWrapper wrapper) {
        switch (wrapper.getMessageType()) {
            case ADD_ROUND_ENTITY:
                RoundEntity roundEntity = GSON.fromJson(wrapper.getMessage(),RoundEntity.class);
                this.gameService.addRoundEntity(roundEntity);
                break;
            case SORT_ROUND:
                this.gameService.sortRound();
                break;
            case NEXT_TURN:
                this.gameService.nextTurn();
                break;
            case RESET:
                this.gameService.reset();
                break;
            case ADD_CONDITION:
                ConditionWrapper conditionWrapper = GSON.fromJson(wrapper.getMessage(),ConditionWrapper.class);
                this.gameService.addCondition(conditionWrapper.getName(),conditionWrapper.getCondition());
                break;
        }
        try {
            sendRoundList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRoundList() throws IOException {
//        send(MessageType.ROUND_OUT,GSON.toJson(this.gameService.getRound()));
        webSocketHandler.sendToAll(MessageType.ROUND_OUT,GSON.toJson(this.gameService.getRound()));
    }


    public  void send(MessageType messageType, String message) throws IOException { //todo rework to send to every client
        TextMessage textMessage = new TextMessage(GSON.toJson(new MessageWrapper(messageType,message)));
        if(session.isOpen()){
            session.sendMessage(textMessage);
            System.out.println("message of type " + messageType +" sent");
        }else{
            System.out.println("session "+ session.getId() + " was not open!");
        }
    }

    public boolean isSessionOpen(){
        return session.isOpen();
    }

    public String getSessionId(){
        return session.getId();
    }
}
