package com.exam.service;

import com.exam.domain.Paper;
import com.exam.web.response.GetSelectPapersResponse;

import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/17.
 */
public interface StudentService {

    public Paper getPaper(Long paperId);

    GetSelectPapersResponse getSelectPapers(Long StudentId);  //通过学生id获得该学生的老师发布的试卷id列表

    boolean handUpPaper(Long StudentId, Long Paperid, List<Long> questionId, List<String>answer);    //学生答题完上传考卷答案
}
