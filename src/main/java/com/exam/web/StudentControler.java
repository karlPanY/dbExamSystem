package com.exam.web;

import com.exam.domain.Paper;
import com.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Controller
public class StudentControler {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = RequestUrls.toExam,method = RequestMethod.GET)
    @ResponseBody
    public Paper startExam(@PathVariable Long paperId) {
        if (paperId != 0) {
            return studentService.getPaper(paperId);
        }
        return null;
    }
}
