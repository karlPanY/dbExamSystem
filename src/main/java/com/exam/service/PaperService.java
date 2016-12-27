package com.exam.service;

import com.exam.domain.Paper;
import com.exam.domain.Question;
import com.exam.web.request.ModifyQuestionRequest;
import com.exam.web.request.SetPaperTimeRequest;
import com.exam.web.response.*;

import java.util.Set;

/**
 * Created by NeilHY on 2016/11/15.
 */
public interface PaperService {

    GetAllPapers getAllPapers(Long teacherId);

    GetPaperContent getPaper(Long paperId);//通过试卷id获得试题

    String setPaperTime(SetPaperTimeRequest request);

    String changeQuestion(ModifyQuestionRequest request);

    GetMarkPapers getMarkPapersService(Long teacherId);

    GetStudentIdListForMark getStuIdListForMark(Long paperId);

    GetExactPaperAnswerToMark getExactPaperAnswerToMark(Long paperId, Long studentId);

    Question createtQeustion(String type,String title,String answer,Float score);//根据前面传来的具体内容生成试题

    Float updateAndGetScore(Long paperId,Long studentId); //统计某学生某试卷的总分数


    Set<Paper> getPapersOfTeacher(Long teacherId);//通过老师id获得他发布的试卷

    Float setQuestionScore(Long questionScoreId,Float score);//给题目判分


}
