package com.test.websockets;

import com.test.services.GameService;
import com.test.websockets.messages.MessageType;
import com.test.websockets.messages.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.test.InitiativeApplication.GSON;

/**
 * Created by Ravanys on 21/03/2017.
 */

@Component
public class WebSocketHandler extends TextWebSocketHandler{

    @Autowired
    private GameService gameService;

    private Map<String,SessionHandler> sessions;

    public WebSocketHandler() {
        sessions= new HashMap<>();
    }

    public void sendToAll(MessageType messageType , String message) throws IOException {
        for (SessionHandler handler : sessions.values()) {
            if(handler.isSessionOpen()){
                handler.send(messageType,message);
            }else{
                System.out.println("Session " + handler.getSessionId() + " has been removed because it was not open");
                sessions.remove(handler.getSessionId());
            }
        }
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if("CLOSE".equalsIgnoreCase(message.getPayload())){
            System.out.println("Session closed");
            sessions.remove(session.getId());
            session.close();
        }else{
            SessionHandler currentHandler;
            if(!sessions.containsKey(session.getId())){
                currentHandler = new SessionHandler(gameService, session,session.getId(), this);
                sessions.put(session.getId(),currentHandler); //todo refactor
                System.out.println("new session created for : " + session.getId());
            }else{
                currentHandler = sessions.get(session.getId());
            }
            System.out.println("Received message");
            System.out.println(message.getPayload());
            MessageWrapper wrapper = GSON.fromJson(message.getPayload(),MessageWrapper.class);
            System.out.println(wrapper.getMessage());
            currentHandler.handleMessage(wrapper);
        }
    }






}
