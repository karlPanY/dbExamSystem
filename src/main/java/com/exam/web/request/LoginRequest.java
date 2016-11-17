package com.exam.web.request;

/**
 * Created by NeilHY on 2016/11/16.
 */
public class LoginRequest {
    private String username;
    private String password;
    private int flag;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password, int flag) {
        this.username = username;
        this.password = password;
        this.flag = flag;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
