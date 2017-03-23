package com.test.websockets;

import com.test.services.GameService;
import com.test.websockets.messages.MessageWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

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

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        if("CLOSE".equalsIgnoreCase(message.getPayload())){
            session.close();
        }else{
            SessionHandler currentHandler;
            if(!sessions.containsKey(session.getId())){
                currentHandler = new SessionHandler(gameService, session,session.getId());
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
