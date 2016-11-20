package com.exam.service;

import com.exam.domain.Paper;
import com.exam.domain.Question;

import java.util.Set;

/**
 * Created by uio5 on 2016/11/19.
 */
public class PaperServiceImpl implements PaperService {
    @Override
    public Question createtQeustion(String type, String title, String answer, Float score) {
        return new Question(type,title,answer,score);
    }

    @Override
    public Float updateAndGetScore(long paperId, Long studentId) {
        return null;
    }

    @Override
    public Set<Question> getPaper(Long paperId) {
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

    @Override
    public boolean changeQuestion(Long questionScoreId, String newtitle, String newanswer) {
        return false;
    }
}
