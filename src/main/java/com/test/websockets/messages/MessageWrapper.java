package com.test.websockets.messages;

/**
 * Created by Ravanys on 21/03/2017.
 */
public class MessageWrapper {
    private MessageType messageType;
    private String message;

    public MessageWrapper() {
    }

    public MessageWrapper(MessageType messageType, String message) {
        this.messageType = messageType;
        this.message = message;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
