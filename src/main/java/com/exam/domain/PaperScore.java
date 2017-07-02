package com.exam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/14.
 */
@Entity
public class PaperScore implements Serializable{
    @EmbeddedId
    private PaperScoreId id;

    private Float score=0f;//学生得分,默认为0

    private Float selectScore;//选择题和判断题分数

    private Float qestionScore;//填空题和简答题分数

    @Column(length = 8000)
    private String selectAnswer;//选择题id#选择题答案$选择题id#选择题答案$

    @Column(length = 8000)
    private String fillAnswer;//填空题id#填空题答案$填空题id#填空题答案$

    @Column(length = 8000)
    private String questionAnswer;//问答题id#问答题答案$问答题id#问答题答案$

    @Column(length = 8000)
    private String trueFalseAnswer;//判断题id#判断题答案$判断题id#判断题答案$

    public PaperScore() {
    }

    public PaperScore(PaperScoreId id, Float score, Float selectScore, Float qestionScore, String selectAnswer, String fillAnswer, String questionAnswer, String trueFalseAnswer) {
        this.id = id;
        this.score = score;
        this.selectScore = selectScore;
        this.qestionScore = qestionScore;
        this.selectAnswer = selectAnswer;
        this.fillAnswer = fillAnswer;
        this.questionAnswer = questionAnswer;
        this.trueFalseAnswer = trueFalseAnswer;
    }



    public Float getSelectScore() {
        return selectScore;
    }

    public void setSelectScore(Float selectScore) {
        this.selectScore = selectScore;
    }


    public void setQestionScore(Float qestionScore) {
        this.qestionScore = qestionScore;
    }

    public PaperScoreId getId() {
        return id;
    }

    public void setId(PaperScoreId id) {
        this.id = id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getSelectAnswer() {
        return selectAnswer;
    }

    public void setSelectAnswer(String selectAnswer) {
        this.selectAnswer = selectAnswer;
    }

    public String getFillAnswer() {
        return fillAnswer;
    }

    public void setFillAnswer(String fillAnswer) {
        this.fillAnswer = fillAnswer;
    }

    public String getTrueFalseAnswer() {
        return trueFalseAnswer;
    }

    public void setTrueFalseAnswer(String trueFalseAnswer) {
        this.trueFalseAnswer = trueFalseAnswer;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null || getClass() != obj.getClass())return false;
        PaperScore that= (PaperScore) obj;
        if(getId()!=null ? !getId().equals(that.getId()) : that.getId() != null)return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (getId() != null ? getId().hashCode():0);
    }
}

