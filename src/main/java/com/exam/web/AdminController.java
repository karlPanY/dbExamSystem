package com.exam.web;

import com.exam.domain.StuClass;
import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.service.AdminService;
import com.exam.web.request.AddClassRequest;
import com.exam.web.request.ChangeStudentRequest;
import com.exam.web.request.ChangeTeacherRequest;
import com.exam.web.response.AddClassResponse;
import com.exam.web.response.AdminGetGlassStu;
import com.exam.web.response.AdminGetTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by uio5 on 2016/12/23.
 */
@Controller
public class AdminController {
    private StuClass current_class;

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = RequestUrls.adminInit,method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public  ArrayList<Classes> init(HttpSession session)
    {
        if(session.getAttribute("id")==null)
            return null;
        Set<StuClass> classSet = adminService.getstuClass();

        ArrayList<Classes> classes = new ArrayList<>();
        Iterator classIte = classSet.iterator();
        while(classIte.hasNext())
        {
            StuClass stuClass = (StuClass) classIte.next();
            Classes oneclass = new Classes();
            oneclass.setClass_name(stuClass.getClassName());
            oneclass.setClass_id(stuClass.getClassId());
            classes.add(oneclass);
        }

        return classes;
    }

    @RequestMapping(value = RequestUrls.addClass,method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public AddClassResponse addClass(@RequestBody AddClassRequest addClassRequest,HttpSession session)
    {
        if(session.getAttribute("id")==null)
            return null;
        long class_teacher = addClassRequest.getClass_teacher();
        Teacher teacher = adminService.getTeacherByid(class_teacher);
        String class_name = addClassRequest.getClass_name();
        StuClass stuClass = new StuClass();
        stuClass.setClassName(class_name);
        stuClass.setTeacher(teacher);
        AddClassResponse addClassResponse= adminService.addClass(stuClass);

        return  addClassResponse;
    }


    @RequestMapping(value = RequestUrls.adminGetClassStu,method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public AdminGetGlassStu getClassStu(@RequestParam long class_id)
    {
        StuClass stuClass = adminService.getClassStu(class_id);
        AdminGetGlassStu stu = new AdminGetGlassStu(stuClass.getStudentSet());

        this.current_class = stuClass;
        return stu;
    }

    @RequestMapping(value = RequestUrls.adminGetTea,method = RequestMethod.GET,produces = "application/json")
    @ResponseBody
    public AdminGetTeacher getTeacher(HttpSession session)
    {
        if(session.getAttribute("id")==null)
            return null;
        Set<Teacher> teacherSet = adminService.getTeacher();
        AdminGetTeacher tea = new AdminGetTeacher(teacherSet);

        return tea;
    }

    @RequestMapping(value = RequestUrls.adminAddStudent,method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Msg addStudent(@RequestBody ChangeStudentRequest changeStudentRequest)
    {
        Msg msg = new Msg();
        Student new_student = new Student();
        new_student.setId(changeStudentRequest.getStudent_id());
        new_student.setPassword(changeStudentRequest.getPassword());
        new_student.setStudentName(changeStudentRequest.getStudent_name());
        new_student.setStuClass(adminService.getClassStu(changeStudentRequest.getClass_id()));
        if (changeStudentRequest.isNews())
            msg.setSuccess(adminService.saveNewStudent(new_student));
        else
        {
            msg.setSuccess(adminService.updateStudent(new_student));
        }
        return msg;
    }

    @RequestMapping(value = RequestUrls.adminAddTeacher,method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Msg addTeacher(@RequestBody ChangeTeacherRequest changeTeacherRequest)
    {
        Msg msg = new Msg();
        Teacher new_teacher = new Teacher();
        new_teacher.setId(changeTeacherRequest.getTeacher_id());
        new_teacher.setPassword(changeTeacherRequest.getPassword());
        new_teacher.setTeacherName(changeTeacherRequest.getTeacher_name());
        String []classNames = changeTeacherRequest.getClass_name().split(",");
        if (changeTeacherRequest.isNews())
            msg.setSuccess(adminService.saveNewTeacher(new_teacher,classNames));
        else
        {
            msg.setSuccess(adminService.updateTeacher(new_teacher,classNames));
        }
        return msg;
    }
    @RequestMapping(value = RequestUrls.adminDeleteStudent,method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Msg deleteStdent(@RequestBody ChangeStudentRequest changeStudentRequest)
    {
        Msg msg = new Msg();
        long student_id = changeStudentRequest.getStudent_id();
        msg.setSuccess(adminService.deleteStudent(student_id));
        return msg;
    }

    @RequestMapping(value = RequestUrls.adminDeleteTeacher,method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public Msg deleteTeacher(@RequestBody ChangeTeacherRequest changeTeacherRequest)
    {
        Msg msg = new Msg();
        long teacher_id = changeTeacherRequest.getTeacher_id();
        msg.setSuccess(adminService.deleteTeacher(teacher_id));
        return msg;
    }

    class Classes {
        public long getClass_id() {
            return class_id;
        }

        public void setClass_id(long class_id) {
            this.class_id = class_id;
        }

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }

        long class_id;
        String class_name;
    }

    class Msg
    {
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        boolean success;

    }
}