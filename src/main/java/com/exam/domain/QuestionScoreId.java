package com.exam.domain;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * Created by NeilHY on 2016/11/14.
 */
@Embeddable
public class QuestionScoreId implements Serializable {

    @ManyToOne
    private Paper paper;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Question question;

    public QuestionScoreId() {
    }

    public QuestionScoreId(Paper paper, Student student, Question question) {
        this.paper = paper;
        this.student = student;
        this.question = question;
    }

    public Paper getPaper() {
        return paper;
    }

    public void setPaper(Paper paper) {
        this.paper = paper;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null || getClass() != obj.getClass())return false;
        QuestionScoreId that= (QuestionScoreId) obj;
        if(paper != null ? !paper.equals(that.paper):that.paper!=null)return false;
        if(student != null ? !student.equals(that.student):that.student!=null)return false;
        if(question!=null ? !question.equals(that.question) : that.question != null)return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result;
        result=(paper != null? paper.hashCode() : 0);
        result=97*result+(student!=null ? student.hashCode() : 0);
        result = 199 * result + (question != null ? question.hashCode() : 0);
        return result;
    }
}
