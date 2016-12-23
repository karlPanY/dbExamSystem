package com.exam.service;

import javax.servlet.http.HttpSession;

/**
 * Created by NeilHY on 2016/11/15.
 */
public interface LoginService {
    String login(String userName, String password, int flag, HttpSession session);//登录
}
