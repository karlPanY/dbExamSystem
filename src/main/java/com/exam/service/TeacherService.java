package com.exam.service;

import com.exam.domain.Question;

import java.util.List;

/**
 * Created by NeilHY on 2016/11/17.
 */
public interface TeacherService {
    Long createPaper(String paperName, Long teacherId, List<Question> questions);
}
