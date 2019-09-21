package com.whut.jarvis.askmeclient.bean;

/**
 * Created by Jarvis on 2019/9/21.
 */

public class UserInfo {
    private String userid;
    private String username;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String userid, String username, String password) {
        this.userid = userid;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
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
}
