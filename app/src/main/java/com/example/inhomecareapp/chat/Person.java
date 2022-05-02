package com.example.inhomecareapp.chat;

public class Person {

    private String username;
    private String userId;
    private String lastMessage;

    public Person(String username, String userId, String lastMessage) {
        this.username = username;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }

    public Person() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
