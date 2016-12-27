package com.exam.service;

import com.exam.domain.*;
import com.exam.domain.dao.*;
import com.exam.web.request.ModifyQuestionRequest;
import com.exam.web.request.SetPaperTimeRequest;
import com.exam.web.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by uio5 on 2016/11/19.
 */
@Service("paperService")
public class PaperServiceImpl implements PaperService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PaperRepository paperRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private PaperScoreRepository paperScoreRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public GetAllPapers getAllPapers(Long teacherId) {
        Teacher teacher;
        if ((teacher = teacherRepository.findOne(teacherId)) != null) {
            return new GetAllPapers(teacher.getId(), teacher.getTeacherName(), getPaperList(teacher.getPaperSet()));
        }
        return null;
    }
    private List<GetAllPapers.PaperInfo> getPaperList(Set<Paper> paperSet){
        List<GetAllPapers.PaperInfo> paperInfoList = new ArrayList<>();
        for (Paper paper : paperSet) {
            paperInfoList.add(new GetAllPapers().new PaperInfo(paper.getPaperId(),paper.getPaperName(), paper.getPaperStart(), paper.getPaperEnd()));
        }
        return paperInfoList;
    }

    @Override
    public GetPaperContent getPaper(Long paperId) {
        Paper paper;
        if ((paper = paperRepository.findOne(paperId)) != null) {
            List<GetPaperContent.QuestionInfo> questionInfoList = new ArrayList<>();
            for (Question question : paper.getQuestions()) {
                questionInfoList.add(new GetPaperContent().new QuestionInfo(question.getQuestionId(),question.getType(),question.getTitle(),question.getScore(),question.getAnswer()));
            }
            return new GetPaperContent(questionInfoList.size(), questionInfoList);
        }
        return null;
    }

    @Override
    public String setPaperTime(SetPaperTimeRequest request) {
        Paper paper;
        if ((paper = paperRepository.findOne(request.getPaper_id())) != null) {
            try {
                paper.setPaperStart(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getPaper_start()));
                paper.setPaperEnd(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(request.getPaper_end()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return paperRepository.save(paper)!=null?"true":"false";
        }
        return "false";
    }

    @Override
    public String changeQuestion(ModifyQuestionRequest request) {
        ModifyQuestionRequest.questionInfo[] questionInfoList=request.getQuestionList();
        String flag="false";
        for (ModifyQuestionRequest.questionInfo questionInfo : questionInfoList) {
            if (questionInfo.getQuestion_id() != null) {
                Question question = questionRepository.findOne(questionInfo.getQuestion_id());
                if (questionInfo.getQuestion_answer() != null) {
                    question.setAnswer(questionInfo.getQuestion_answer());
                }
                if (questionInfo.getQuestion_score() != null) {
                    question.setScore(questionInfo.getQuestion_score());
                }
                if (questionInfo.getQuestion_title() != null) {
                    question.setTitle(questionInfo.getQuestion_title());
                }
                if (questionInfo.getType() != null) {
                    question.setType(questionInfo.getType());
                }
                if(questionRepository.save(question) == null){
                    flag = "false";
                    break;
                }else{
                    flag = "true";
                }
            }
        }
        return flag;
    }

    @Override
    public GetMarkPapers getMarkPapersService(Long teacherId) {//获取所有未评阅的试卷id
        Teacher teacher;
        List<GetMarkPapers.papersMarkInfo> papersMarkInfoList = new ArrayList<>();
        if ((teacher = teacherRepository.findOne(teacherId)) != null) {
            for (Paper paper : teacher.getPaperSet()) {
                if(paper.getFlag()==null || paper.getFlag()==0) {//未评阅的试卷
                    papersMarkInfoList.add(new GetMarkPapers().new papersMarkInfo(paper.getPaperId(), paper.getPaperName()));
                }
            }
            return new GetMarkPapers(teacher.getId(), teacher.getTeacherName(), papersMarkInfoList);
        }
        return null;
    }

    @Override
    public GetStudentIdListForMark getStuIdListForMark(Long paperId) {//获取所有该考卷对应的学生id
//        Question question1 = questionRepository.save(new Question("简答题", "你哈哈哈哈哈哈哈哈哈哈哈", "没有答案哈哈哈哈哈", 4f));
//        Question question2 = questionRepository.save(new Question("判断题", "你哈哈哈哈哈哈哈哈哈哈哈", "没有答案哈哈哈哈哈", 4f));
//        questions.clear();
//        questions.addAll(questionSet);

        List<GetStudentIdListForMark.studentNameAndId> studentNameAndIdList = new ArrayList<>();
        List<Long> studentIdList;
        if ((studentIdList = paperScoreRepository.getAllStudentIdByPaperId(paperId)) != null) {
            for (Long id : studentIdList) {
                studentNameAndIdList.add(new GetStudentIdListForMark().new studentNameAndId(id, studentRepository.findOne(id).getStudentName()));
            }
            return new GetStudentIdListForMark(paperId,studentNameAndIdList);
        }
        return null;
    }

    @Override
    public GetExactPaperAnswerToMark getExactPaperAnswerToMark(Long paperId, Long studentId) {//获得非选择题的学生答案并且传回前端
        PaperScore paperScore;
        Student student;
        if ((paperScore = paperScoreRepository.getExactPaperScore(paperId, studentId)) != null && (student = studentRepository.findOne(studentId))!=null) {
            List<GetExactPaperAnswerToMark.paperAnswer> paperAnswerList = new ArrayList<>();
            if(paperScore.getFillAnswer()!=null && ! paperScore.getFillAnswer().equals("")){
                paperAnswerList.addAll(getOnePaperAnswer(paperScore.getFillAnswer()));
            }
            if(paperScore.getQuestionAnswer()!=null && ! paperScore.getQuestionAnswer().equals("")){
                paperAnswerList.addAll(getOnePaperAnswer(paperScore.getQuestionAnswer()));
            }
            return new GetExactPaperAnswerToMark(studentId, student.getStudentName(), paperAnswerList, paperScore.getScore());
        }
        return null;
    }

    private List<GetExactPaperAnswerToMark.paperAnswer> getOnePaperAnswer(String answer) {//将非选择题的字符串解析成指定格式
        String[] singleItem=answer.split("\\$");//3#爱国主义$
        List<GetExactPaperAnswerToMark.paperAnswer> paperAnswerList = new ArrayList<>();
        for (String str : singleItem) {
            if(!str.equals("") && str.contains("#")){
                String[] idAndAnswer = str.split("#");
                Long questionId= Long.valueOf(idAndAnswer[0].trim());
                Question question;
                if((question= questionRepository.findOne(questionId))!=null){
                    paperAnswerList.add(new GetExactPaperAnswerToMark().new paperAnswer(questionId, question.getTitle(), idAndAnswer[1], question.getScore()));
                }
            }
        }
        return paperAnswerList;
    }


    private Float getSelectScore(String selectAnswer){//求选择题和判断题的分数
        Float selectScore=0f;
        String[] singleItem=selectAnswer.split("\\$");//3#爱国主义$
        for (String str : singleItem) {
            if(!str.equals("") && str.contains("#")){
                String[] idAndAnswer = str.split("#");
                Long questionId= Long.valueOf(idAndAnswer[0].trim());
                Question question;
                if((question= questionRepository.findOne(questionId))!=null){
                    if( (question.getType().equals("选择题")||question.getType().equals("判断题")) && question.getAnswer().trim().equals(idAndAnswer[1].trim())){
                        selectScore+=question.getScore();
                    }
                }
            }
        }
        return selectScore;

    }


    @Override
    public Question createtQeustion(String type, String title, String answer, Float score) {
        return new Question(type,title,answer,score);
    }

    @Override
    public Float updateAndGetScore(Long paperId, Long studentId) {
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

}
