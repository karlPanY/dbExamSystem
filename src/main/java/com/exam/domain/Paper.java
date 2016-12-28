package com.exam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/13.
 */
@Entity
public class Paper implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "paperId")
    private Long paperId;

    @Column(nullable = false)
    private String paperName;

    private Date paperStart;//试卷开始时间

    private Date paperEnd;//试卷结束时间

    private Integer flag=0;//标记该试卷是否已经被评阅 0 未评阅 1 已评阅

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private List<Question> questions;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId",referencedColumnName = "teacherId")
    private Teacher teacherOf;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id.paper",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE})
    private Set<PaperScore> paperScoreSet;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id.paper",cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.MERGE})
    private Set<QuestionScore> questionScoreSet;

    public Paper() {
    }

    public Paper(String paperName, String paperStart, String paperEnd,Integer flag, List<Question> questions, Teacher teacherOf, Set<PaperScore> paperScoreSet, Set<QuestionScore> questionScoreSet) throws ParseException {
        this.paperName = paperName;
        this.paperStart = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paperStart);
        this.paperEnd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(paperEnd);
        this.flag=flag;
        this.questions = questions;
        this.teacherOf = teacherOf;
        this.paperScoreSet = paperScoreSet;
        this.questionScoreSet = questionScoreSet;
    }

    public Set<QuestionScore> getQuestionScoreSet() {
        return questionScoreSet;
    }

    public void setQuestionScoreSet(Set<QuestionScore> questionScoreSet) {
        this.questionScoreSet = questionScoreSet;
    }

    public Long getPaperId() {
        return paperId;
    }

    public void setPaperId(Long paperId) {
        this.paperId = paperId;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Date getPaperStart() {
        return paperStart;
    }

    public void setPaperStart(Date paperStart) {
        this.paperStart = paperStart;
    }

    public Date getPaperEnd() {
        return paperEnd;
    }

    public void setPaperEnd(Date paperEnd) {
        this.paperEnd = paperEnd;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Teacher getTeacherOf() {
        return teacherOf;
    }

    public void setTeacherOf(Teacher teacherOf) {
        this.teacherOf = teacherOf;
    }

    public Set<PaperScore> getPaperScoreSet() {
        return paperScoreSet;
    }

    public void setPaperScoreSet(Set<PaperScore> paperScoreSet) {
        this.paperScoreSet = paperScoreSet;
    }
}
