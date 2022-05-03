package com.example.inhomecareapp.customer;

public class CustomerPost {

    private String UserId;
    private String username;
    private String postId;
    private String postContent;
    private boolean accept = false;

    public CustomerPost() {
    }

    public CustomerPost(String userId, String username, String postId, String postContent) {
        UserId = userId;
        this.username = username;
        this.postId = postId;
        this.postContent = postContent;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    @Override
    public String toString() {
        return "CustomerPost{" +
                "UserId='" + UserId + '\'' +
                ", postId='" + postId + '\'' +
                ", postContent='" + postContent + '\'' +
                '}';
    }
}
