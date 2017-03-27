package com.test.websockets;

import com.google.gson.Gson;
import com.test.domain.RoundEntity;
import com.test.services.GameService;
import com.test.websockets.messages.InitiativeListMessage;
import com.test.websockets.messages.InitiativeMessage;
import com.test.websockets.messages.MessageType;
import com.test.websockets.messages.MessageWrapper;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

import static com.test.InitiativeApplication.GSON;
import static com.test.websockets.messages.MessageType.INITIATIVE_OUT;
import static com.test.websockets.messages.MessageType.ROUND_LIST;

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
            case INITIATIVE_IN:
                System.out.println("Initiative Message received");
                InitiativeMessage initiativeMessage = GSON.fromJson(wrapper.getMessage(), InitiativeMessage.class);
                gameService.setOrAddInitiative(initiativeMessage.getPlayerId(), initiativeMessage.getInitiative());
                System.out.println("initiative set!");
                try {
                    sendInitiativeList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case REGISTER_ROUNDENTITY:
                System.out.println("Round entity received");
                RoundEntity roundEntity = GSON.fromJson(wrapper.getMessage(),RoundEntity.class);
                gameService.addRoundEntity(roundEntity);
                System.out.println("roundentity added");

                try {
                    sendRoundList();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;

        }
    }

    private void sendRoundList() throws IOException {
        for (RoundEntity roundEntity : gameService.getRoundSortedByInitiative()){
            System.out.println(roundEntity);
        }

        String message  = GSON.toJson(gameService.getRoundSortedByInitiative());
        System.out.println(message);

        send(ROUND_LIST,GSON.toJson(gameService.getRoundSortedByInitiative()));
    }

    public void sendInitiativeList() throws IOException {
        send(INITIATIVE_OUT,GSON.toJson(gameService.getSortedList()));
    }


    private void send(MessageType messageType, String message) throws IOException {
        TextMessage textMessage = new TextMessage(GSON.toJson(new MessageWrapper(messageType,message)));
        session.sendMessage(textMessage);
        System.out.println("message of type " + messageType +" sent");
    }
}
