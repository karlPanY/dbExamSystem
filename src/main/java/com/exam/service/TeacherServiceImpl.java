package com.exam.service;

import com.exam.domain.Question;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Service("teacherService")
public class TeacherServiceImpl implements TeacherService {
    @Override
    public Long createPaper(String paperName, Long teacherId, List<Question> questions) {// TODO: 2016/11/17 测试取出的teacher里的各个set项的值

        return null;
    }
}
