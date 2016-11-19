package com.exam.service;

import com.exam.domain.*;
import com.exam.domain.dao.PaperRepository;
import com.exam.domain.dao.QuestionRepository;
import com.exam.domain.dao.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Long createPaper(String paperName, Long teacherId, List<Question> questions) {// TODO: 2016/11/17 测试取出的teacher里的各个set项的值
        // TODO: 2016/11/17 测试能不能直接save paper，而不用先save各个题目，最后再放进paper里。
        Teacher teacher;
        if ((teacher = teacherRepository.findOne(teacherId)) != null) {
            Paper paper = new Paper(paperName, null, null, questions, teacher, null, null);
            paper=paperRepository.save(paper);
            /*
            Iterator<Question> ite = questions.iterator();
            while(ite.hasNext())
            {
                Question q=ite.next();
                q.setPaperOf(paper);
                questionRepository.save(q);
            }
            我感觉没那么智能  还是这样一个个插入吧*/
            return paper.getPaperId();
        }
        return null;
    }

    @Override
    public Long publishPaper(Long paperId, Date beginTime, Date endTime) {
        return null;
    }

    @Override
    public Set<StuClass> getClassList(Long teacherId) {
        return null;
    }

    @Override
    public Set<Student> getClassStudent(Long classId) {
        return null;
    }

    @Override
    public Set<PaperScore> getClassGrade(long StuclassId, long Paperid) {
        return null;
    }

    @Override
    public Set<PaperScore> getStudentGrade(long StudentId) {
        return null;
    }

    @Override
    public PaperScore getStudentPaperGrade(Long StudentId, Long PaperId) {
        return null;
    }

    @Override
    public Set<QuestionScore> getStudentAnswer(Long studentId, Long Paperid) {
        return null;
    }
}
