package com.exam.web;

import com.exam.service.LoginService;
import com.exam.web.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by NeilHY on 2016/11/15.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = RequestUrls.toLoginUrl, method = RequestMethod.POST)
//    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest) {
        System.out.println("登录。");
//        if (loginRequest != null) {
//            if (loginRequest.getUsername() != null && loginRequest.getPassword() != null &&loginRequest.getFlag()>0) {
////                return loginService.login(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getFlag());
//                return "select";
//            }
//        }
//        return null;
        return "exam";
    }



}
