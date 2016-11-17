package com.exam.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * Created by NeilHY on 2016/11/14.
 */
@Entity
public class QuestionScore implements Serializable {

    @EmbeddedId
    private QuestionScoreId id;

    private Float scoreStu;//学生这道题的得分

    private String answerStu;//学生的答案

    public QuestionScore() {
    }

    public QuestionScore(QuestionScoreId id, Float scoreStu, String answerStu) {
        this.id = id;
        this.scoreStu = scoreStu;
        this.answerStu = answerStu;
    }

    public QuestionScoreId getId() {
        return id;
    }

    public void setId(QuestionScoreId id) {
        this.id = id;
    }

    public Float getScoreStu() {
        return scoreStu;
    }

    public void setScoreStu(Float scoreStu) {
        this.scoreStu = scoreStu;
    }

    public String getAnswerStu() {
        return answerStu;
    }

    public void setAnswerStu(String answerStu) {
        this.answerStu = answerStu;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null || getClass() != obj.getClass())return false;
        QuestionScore that= (QuestionScore) obj;
        if(getId()!=null ? !getId().equals(that.getId()) : that.getId() != null)return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (getId() != null ? getId().hashCode():0);
    }
}
