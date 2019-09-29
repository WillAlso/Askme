package com.whut.jarvis.askmeclient.bean;

/**
 * Created by Jarvis on 2019/9/21.
 */

public class User {
    private int userid;
    private String username;
    private String password;
    private int status;
    private String description;
    private int identity;
    private String face_token;

    public User() {
    }

    public User(int userid, String username, String password, int status, String description, int identity, String face_token) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.description = description;
        this.identity = identity;
        this.face_token = face_token;
    }

    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", identity=" + identity +
                ", face_token='" + face_token + '\'' +
                '}';
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdentity() {
        return identity;
    }

    public void setIdentity(int identity) {
        this.identity = identity;
    }

    public String getFace_token() {
        return face_token;
    }

    public void setFace_token(String face_token) {
        this.face_token = face_token;
    }
}
