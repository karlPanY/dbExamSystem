package com.exam.web;

import com.exam.service.PaperService;
import com.exam.web.request.ModifyQuestionRequest;
import com.exam.web.request.SetPaperTimeRequest;
import com.exam.web.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by NeilHY on 2016/12/26.
 */
@Controller
public class PaperController {
    @Autowired
    private PaperService paperService;

    @RequestMapping(value = RequestUrls.getAllPapers, method = RequestMethod.GET)
    @ResponseBody
    public GetAllPapers getAllPapers(HttpSession session) {
        if (session.getAttribute("id") != null) {
            return paperService.getAllPapers((Long) session.getAttribute("id"));
        }
        return null;
    }

    @RequestMapping(value = RequestUrls.getPaperContent,method = RequestMethod.GET)
    @ResponseBody
    public GetPaperContent getPaperContent(@PathVariable Long paper_id) {
        if (paper_id != null) {
            return paperService.getPaper(paper_id);
        }
        return null;
    }

    @RequestMapping(value = RequestUrls.setPaperTime, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String setPaperTime(@RequestBody SetPaperTimeRequest request) {
        if (request != null && request.getPaper_id() != null && request.getPaper_start() != null && request.getPaper_end() != null) {
            return paperService.setPaperTime(request);
        }
        return "false";
    }

    @RequestMapping(value = RequestUrls.changeQuestions, method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public String changeQuestions(@RequestBody ModifyQuestionRequest request) {
        if (request != null && request.getQuestionList() != null) {
            return paperService.changeQuestion(request);
        }
        return "false";
    }

    @RequestMapping(value = RequestUrls.getMarkPapers, method = RequestMethod.GET)
    @ResponseBody
    public GetMarkPapers getMarkPapers(HttpSession session) {
        if (session.getAttribute("id") != null) {
            return paperService.getMarkPapersService((Long) session.getAttribute("id"));
        }
        return null;
    }

    @RequestMapping(value = RequestUrls.getStudentIdListForMark,method = RequestMethod.GET)
    @ResponseBody
    public GetStudentIdListForMark getStudentIdListForMark(@PathVariable Long paper_id) {
        if (paper_id != null) {
            return paperService.getStuIdListForMark(paper_id);
        }
        return null;
    }

    @RequestMapping(value = RequestUrls.getMarkingPaper,method = RequestMethod.GET)
    @ResponseBody
    public GetExactPaperAnswerToMark getExactPaperAnswerToMark(@PathVariable Long paper_id, @PathVariable Long student_id) {
        if (paper_id != null &&student_id!=null) {
            return paperService.getExactPaperAnswerToMark(paper_id, student_id);
        }
        return null;
    }
}
