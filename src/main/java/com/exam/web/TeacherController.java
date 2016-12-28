package com.exam.web;

import com.exam.service.TeacherService;
import com.exam.web.request.CreatePaper;
import com.exam.web.request.LoginRequest;
import com.exam.web.response.GetAllPapers;
import com.exam.web.response.GetClassStudents;
import com.exam.web.response.GetClassesResponse;
import com.exam.web.response.GetStuGradeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Controller
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    public Long createPaper(){
        // TODO: 2016/11/17 测试能不能直接把json变成对象 
        return 0L;
    }

    @RequestMapping(value = RequestUrls.getAllClasses, method = RequestMethod.GET)
    @ResponseBody
    public GetClassesResponse getAllClassesResponse(HttpSession session) {
        if(session.getAttribute("id")!=null){
            return teacherService.getClassList(Long.parseLong(session.getAttribute("id").toString()));
        }
        return null;//表示session里没有id，要么没有登录，要么session过期。
    }

    @RequestMapping(value = RequestUrls.getClassStu,method = RequestMethod.GET)
    @ResponseBody
    public GetClassStudents getClassStu(@PathVariable Long class_id){
        if (class_id != null) {
            return teacherService.getClassStudent(class_id);
        }
        return null;
    }

    @RequestMapping(value = RequestUrls.getStuGrade, method = RequestMethod.GET)
    @ResponseBody
    public GetStuGradeResponse getStuGrades(@PathVariable Long teacher_id, @PathVariable Long student_id) {
        System.out.println("student_id:"+student_id);
        if (teacher_id != null && student_id != null) {
            return teacherService.getStudentGrade(student_id);
        }
        return null;
    }
    @RequestMapping(value = RequestUrls.createPaper, method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public String createPaper(@RequestBody CreatePaper createPaper, HttpSession session) {
        if (session.getAttribute("id") != null && createPaper.getPaperName() != null) {
            return teacherService.createPaper((Long) session.getAttribute("id"), createPaper);
        }
        return null;
    }
}
