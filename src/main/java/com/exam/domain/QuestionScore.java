package com.exam.domain;

import javax.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumns({@JoinColumn(name = "paperId", referencedColumnName = "paper_paper_id"),
            @JoinColumn(name = "studentId", referencedColumnName = "student_student_id")})
    private PaperScore paperScore;

    public QuestionScore() {
    }

    public QuestionScore(QuestionScoreId id, Float scoreStu, String answerStu, PaperScore paperScore) {
        this.id = id;
        this.scoreStu = scoreStu;
        this.answerStu = answerStu;
        this.paperScore = paperScore;
    }

    public PaperScore getPaperScore() {
        return paperScore;
    }

    public void setPaperScore(PaperScore paperScore) {
        this.paperScore = paperScore;
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
