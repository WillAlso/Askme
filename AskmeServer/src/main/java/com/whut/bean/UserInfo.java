package com.whut.bean;

public class UserInfo {
    private int userid;
    private String username;
    private String password;
    private int status;
    private String description;
    private int identity;

    public UserInfo() {
    }

    public UserInfo(int userid, String username, String password, int status, String description, int identity) {
        this.userid = userid;
        this.username = username;
        this.password = password;
        this.status = status;
        this.description = description;
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", identity=" + identity +
                '}';
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
}
