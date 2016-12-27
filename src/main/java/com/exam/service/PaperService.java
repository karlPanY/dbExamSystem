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

    String setStudentNoneSelectScore(Long paperId, Long studentId, Float score);

    GetAllMarkedPapersInfo getAllMarkedPapersInfo(Long teacherId);

    GetStudentScoreByPaperId getStudentScoreByPaperId(Long paperId);
}
