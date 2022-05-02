package com.example.inhomecareapp.chat;


public class Message {

    private String id;

    private String message;

    private String userId;


    public Message(String id, String message, String userId) {
        this.id = id;
        this.message = message;
        this.userId = userId;
    }

    public Message() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}