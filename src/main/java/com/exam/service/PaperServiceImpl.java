package com.exam.service;

import com.exam.domain.Paper;
import com.exam.domain.Question;
import com.exam.domain.Teacher;
import com.exam.domain.dao.PaperRepository;
import com.exam.domain.dao.QuestionRepository;
import com.exam.domain.dao.TeacherRepository;
import com.exam.web.request.ModifyQuestionRequest;
import com.exam.web.request.SetPaperTimeRequest;
import com.exam.web.response.GetAllPapers;
import com.exam.web.response.GetPaperContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by uio5 on 2016/11/19.
 */
@Service("paperService")
public class PaperServiceImpl implements PaperService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Override
    public GetAllPapers getAllPapers(Long teacherId) {
        Teacher teacher;
        if ((teacher = teacherRepository.findOne(teacherId)) != null) {
            return new GetAllPapers(teacher.getId(), teacher.getTeacherName(), getPaperList(teacher.getPaperSet()));
        }
        return null;
    }
    private List<GetAllPapers.PaperInfo> getPaperList(Set<Paper> paperSet){
        List<GetAllPapers.PaperInfo> paperInfoList = new ArrayList<>();
        for (Paper paper : paperSet) {
            paperInfoList.add(new GetAllPapers().new PaperInfo(paper.getPaperId(),paper.getPaperName(), paper.getPaperStart(), paper.getPaperEnd()));
        }
        return paperInfoList;
    }

    @Override
    public GetPaperContent getPaper(Long paperId) {
        Paper paper;
        if ((paper = paperRepository.findOne(paperId)) != null) {
            List<GetPaperContent.QuestionInfo> questionInfoList = new ArrayList<>();
            for (Question question : paper.getQuestions()) {
                questionInfoList.add(new GetPaperContent().new QuestionInfo(question.getQuestionId(),question.getType(),question.getTitle(),question.getScore(),question.getAnswer()));
            }
            return new GetPaperContent(questionInfoList.size(), questionInfoList);
        }
        return null;
    }

    @Override
    public String setPaperTime(SetPaperTimeRequest request) {
        Paper paper;
        if ((paper = paperRepository.findOne(request.getPaper_id())) != null) {
            try {
                paper.setPaperStart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getPaper_start()));
                paper.setPaperEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getPaper_end()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return paperRepository.save(paper)!=null?"true":"false";
        }
        return "false";
    }

    @Override
    public String changeQuestion(ModifyQuestionRequest request) {
        ModifyQuestionRequest.questionInfo[] questionInfoList=request.getQuestionList();
        String flag="false";
        for (ModifyQuestionRequest.questionInfo questionInfo : questionInfoList) {
            if (questionInfo.getQuestion_id() != null) {
                Question question = questionRepository.findOne(questionInfo.getQuestion_id());
                if (questionInfo.getQuestion_answer() != null) {
                    question.setAnswer(questionInfo.getQuestion_answer());
                }
                if (questionInfo.getQuestion_score() != null) {
                    question.setScore(questionInfo.getQuestion_score());
                }
                if (questionInfo.getQuestion_title() != null) {
                    question.setTitle(questionInfo.getQuestion_title());
                }
                if (questionInfo.getType() != null) {
                    question.setType(questionInfo.getType());
                }
                if(questionRepository.save(question) == null){
                    flag = "false";
                    break;
                }else{
                    flag = "true";
                }
            }
        }
        return flag;
    }

    @Override
    public Question createtQeustion(String type, String title, String answer, Float score) {
        return new Question(type,title,answer,score);
    }

    @Override
    public Float updateAndGetScore(Long paperId, Long studentId) {
        return null;
    }

    @Override
    public Set<Paper> getPapersOfTeacher(Long teacherId) {
        return null;
    }

    @Override
    public Float setQuestionScore(Long questionScoreId, Float score) {
        return null;
    }

}
