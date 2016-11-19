package com.exam.service;

import com.exam.domain.Paper;
import com.exam.domain.Question;
import com.exam.domain.Teacher;
import com.exam.domain.dao.PaperRepository;
import com.exam.domain.dao.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Long createPaper(String paperName, Long teacherId, List<Question> questions) {// TODO: 2016/11/17 测试取出的teacher里的各个set项的值
        // TODO: 2016/11/17 测试能不能直接save paper，而不用先save各个题目，最后再放进paper里。
        Teacher teacher;
        if ((teacher = teacherRepository.findOne(teacherId)) != null) {
            Paper paper = new Paper(paperName, null, null, questions, teacher, null, null);
            paper=paperRepository.save(paper);
            return paper.getPaperId();
        }
        return null;
    }
}
