package com.exam.service;

import com.exam.domain.*;
import com.exam.domain.dao.PaperRepository;
import com.exam.domain.dao.PaperScoreRepository;
import com.exam.domain.dao.QuestionRepository;
import com.exam.domain.dao.StudentRepository;
import com.exam.web.response.GetAllMarkedPapersInfo;
import com.exam.web.response.GetSelectPapersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PaperScoreRepository paperScoreRepository;

    @Override
    public Paper getPaper(Long paperId) {
        return paperRepository.findOne(paperId);
    }

    @Override
    public GetSelectPapersResponse getSelectPapers(Long studentId) {
        Student student;
        if ((student = studentRepository.findOne(studentId)) != null) {
            Teacher teacher=student.getStuClass().getTeacher();
            Set<Paper> paperSet;
            if ((paperSet = teacher.getPaperSet()) != null) {
                List<GetSelectPapersResponse.papersSelectInfo> papersSelectInfoList = new ArrayList<>();
                for (Paper paper : paperSet) {
                    if (paper.getPaperStart() != null && paper.getPaperEnd() != null) {
                        papersSelectInfoList.add(new GetSelectPapersResponse().new papersSelectInfo(paper.getPaperId(), paper.getPaperName(),
                                new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(paper.getPaperStart()), new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(paper.getPaperEnd())));
                    }
                }
                return new GetSelectPapersResponse(studentId, student.getStudentName(), papersSelectInfoList);
            }
        }
        return null;
    }


    @Override
    public Student getStudent(long id) {
        return studentRepository.findOne(id);
    }

    @Override
    public Question getQuestion(long question_id) {
        return  questionRepository.findOne(question_id);
    }

    @Override
    public void saveAnswer(PaperScore paperScore) {
        paperScoreRepository.save(paperScore);
    }
}
