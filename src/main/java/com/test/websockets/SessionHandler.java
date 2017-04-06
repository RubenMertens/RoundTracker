package com.test.websockets;

import com.test.domain.RoundEntity;
import com.test.services.GameService;
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

    public SessionHandler(GameService gameService, WebSocketSession session, String id) {
        this.gameService = gameService;
        this.session = session;
        this.id = id;
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
        }
        try {
            sendRoundList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendRoundList() throws IOException {
        send(MessageType.ROUND_OUT,GSON.toJson(this.gameService.getRound()));
    }



    private void send(MessageType messageType, String message) throws IOException { //todo rework to send to every client
        TextMessage textMessage = new TextMessage(GSON.toJson(new MessageWrapper(messageType,message)));
        session.sendMessage(textMessage);
        System.out.println("message of type " + messageType +" sent");
    }
}
