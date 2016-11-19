package com.exam.service;

import com.exam.domain.Paper;
import com.exam.domain.Question;

import java.util.Set;

/**
 * Created by NeilHY on 2016/11/15.
 */
public interface PaperService {
    Question createtQeustion(String type,String title,String answer,Float score);//根据前面传来的具体内容生成试题

    Float updateAndGetScore(long paperId,Long studentId); //统计某学生某试卷的总分数

    Set<Question> getPaper(Long paperId);//通过试卷id获得试题

    Set<Paper> getPapersOfTeacher(Long teacherId);//通过老师id获得他发布的试卷

    Float setQuestionScore(Long questionScoreId,Float score);//给题目判分

    boolean changeQuestion(Long questionScoreId,String newtitle,String newanswer);//修改题目，若题目没改就传null，只传答案进去吧
}
