package com.exam.service;

import com.exam.domain.Admin;
import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.domain.dao.AdminRepository;
import com.exam.domain.dao.StudentRepository;
import com.exam.domain.dao.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by NeilHY on 2016/11/15.
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public String login(String userName, String password, int flag, HttpSession session) {
        switch (flag) {
            case 1:
                Admin admin;
                if( (admin=adminRepository.findByAdminName(userName)) != null){
                    if (admin.getPassword().equals(password)) {
                        session.setAttribute("id",admin.getAdminId());
                        return "admin";
                    }
                    return null;
                }
                break;
            case 2:
                Teacher teacher;
                Long tid = Long.parseLong(userName);
                if ( (teacher=teacherRepository.findOne(tid)) != null) {
                    if(teacher.getPassword().equals(password)){
                        session.setAttribute("id",teacher.getId());
                        return "teacherIndex";
                    }
                    return null;
                }
                break;
            case 3:
                Student student;
                Long sid = Long.parseLong(userName);
                if ( (student=studentRepository.findOne(sid)) != null) {
                    if (student.getPassword().equals(password)) {
                        session.setAttribute("id",student.getId());
                        return "select";
                    }
                    return  null;
                }
                break;
        }
        return null;
    }
}
