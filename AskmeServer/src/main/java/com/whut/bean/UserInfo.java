package com.whut.bean;

public class UserInfo {
    private int userid;
    private String username;

    public UserInfo() {
    }

    public UserInfo(int userid, String username) {
        this.userid = userid;
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
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
}
