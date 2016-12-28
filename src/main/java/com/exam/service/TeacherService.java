package com.exam.service;

import com.exam.domain.*;
import com.exam.web.request.CreatePaper;
import com.exam.web.response.GetClassStudents;
import com.exam.web.response.GetClassesResponse;
import com.exam.web.response.GetStuGradeResponse;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/17.
 */
public interface TeacherService {
    Long createPaper(String paperName, Long teacherId, List<Question> questions); //创建试卷

    Long publishPaper(Long paperId, Date beginTime, Date endTime);//发布试卷

    GetClassesResponse getClassList(Long teacherId);//获得老师授课的班级

    GetClassStudents getClassStudent(Long classId);//获得某班级具体学生信息

    Set<PaperScore> getClassGrade(Long StuclassId, Long Paperid);//获得某班级某试卷的全班成绩

    GetStuGradeResponse getStudentGrade(Long StudentId);//获得某学生的所有试卷成绩

    PaperScore getStudentPaperGrade(Long StudentId,Long PaperId);//获得某学生某试卷成绩

    Set<QuestionScore> getStudentAnswer(Long studentId, Long Paperid);//获得某学生某试卷答案

    String createPaper(Long teacherId, CreatePaper createPaper);
}
