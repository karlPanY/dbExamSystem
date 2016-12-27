package com.exam.service;

import com.exam.domain.*;
import com.exam.domain.dao.PaperRepository;
import com.exam.domain.dao.PaperScoreRepository;
import com.exam.domain.dao.QuestionRepository;
import com.exam.domain.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Set<Long> getAvilablePaper(Long StudentId) {
        return null;
    }

    @Override
    public boolean handUpPaper(Long StudentId, Long Paperid, List<Long> questionIdList, List<String> answerList) {
        Iterator<Long> iteratorOfquestionId = questionIdList.iterator();
        Iterator<String> iteratorOfanswer = answerList.iterator();
        Paper paper;
        Student student;

        if ((paper = paperRepository.findOne(Paperid)) != null&&((student = studentRepository.findOne(StudentId))!=null)) {
            //先存一个PaperScore
            PaperScoreId paperScoreId = new PaperScoreId(paper,student);
            PaperScore paperScore = new PaperScore();
            paperScoreRepository.save(paperScore);

            //在依次存QuestionScore
            while (iteratorOfquestionId.hasNext() && iteratorOfanswer.hasNext()) {
                Long questionId = iteratorOfquestionId.next();String answer = iteratorOfanswer.next();
                Question question = questionRepository.findOne(questionId);
                Float score = question.getAnswer().equalsIgnoreCase(answer)?question.getScore():0;//判断一下是否得分

                QuestionScoreId questionScoreId = new QuestionScoreId(paper,student,question);
                QuestionScore questionScore = new QuestionScore(questionScoreId,score,answer,paperScore);
            }
            return true;
        }
        else
            return false;
    }
}
