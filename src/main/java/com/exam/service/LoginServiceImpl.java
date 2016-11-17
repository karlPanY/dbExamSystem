package com.exam.service;

import com.exam.domain.Admin;
import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.domain.dao.AdminRepository;
import com.exam.domain.dao.StudentRepository;
import com.exam.domain.dao.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String login(String userName, String password, int flag) {
        switch (flag) {
            case 1:
                Admin admin;
                if( (admin=adminRepository.findByAdminName(userName)) != null){
                    return admin.getPassword().equals(password) ? "adminPage" : null;
                }
                break;
            case 2:
                Teacher teacher;
                Long tid = Long.parseLong(userName);
                if ( (teacher=teacherRepository.findByTeacherId(tid)) != null) {
                    return teacher.getPassword().equals(password) ? "teacherIndex": null;
                }
                break;
            case 3:
                Student student;
                Long sid = Long.parseLong(userName);
                if ( (student=studentRepository.findByStudentId(sid)) != null) {
                    return student.getPassword().equals(password) ? "exam" : null;
                }
                break;
        }
        return null;
    }
}
