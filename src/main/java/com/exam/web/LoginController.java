package com.exam.web;

import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.service.LoginService;
import com.exam.web.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

/**
 * Created by NeilHY on 2016/11/15.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = RequestUrls.toLoginUrl, method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        if (loginRequest != null) {
            if (loginRequest.getUsername() != null && loginRequest.getPassword() != null &&loginRequest.getFlag()>0) {

                return loginService.login(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getFlag(),session);
            }
        }
        return null;
    }



}
