package com.exam.web;

import com.exam.service.StudentService;
import com.exam.web.response.GetSelectPapersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Controller
public class StudentControler {

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = RequestUrls.toExam,method = RequestMethod.GET)
    @ResponseBody
    public String startExam(@PathVariable Long paperId,HttpSession session) {
        if (paperId != 0) {
            session.setAttribute("paperId",paperId);
            return "exam";
        }
        return null;
    }

    @RequestMapping(value = RequestUrls.getSelectPapers,method = RequestMethod.GET)
    @ResponseBody
    public GetSelectPapersResponse getSelectPapers(HttpSession session) {
        if (session.getAttribute("id") != null) {
            return studentService.getSelectPapers((Long) session.getAttribute("id"));
        }
        return null;
    }


}
