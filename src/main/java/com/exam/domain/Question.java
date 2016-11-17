package com.exam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

import static javafx.scene.input.KeyCode.F;

/**
 * Created by NeilHY on 2016/11/13.
 */
@Entity
public class Question implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="questionId")
    private Long questionId;

    @Column(nullable = false, length = 30)
    private String type;

    @Column(nullable = false,columnDefinition = "TEXT")
    private String title;//数据库用Text类型;ABCD选项也放里面

    @Column(nullable = false)
    private String answer;//字母选项或者填空题答案或简答答案

    @Column(nullable = false)
    private Float score;//每到试题的分数

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paperId",referencedColumnName = "paperId")
    private Paper paperOf;//该考卷的题

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id.question",cascade = CascadeType.ALL)
    private Set<QuestionScore> questionScoreSet;

    public Question() {
    }

    public Question(String type, String title, String answer, Float score, Paper paperOf, Set<QuestionScore> questionScoreSet) {
        this.type = type;
        this.title = title;
        this.answer = answer;
        this.score = score;
        this.paperOf = paperOf;
        this.questionScoreSet = questionScoreSet;
    }

    public Set<QuestionScore> getQuestionScoreSet() {
        return questionScoreSet;
    }

    public void setQuestionScoreSet(Set<QuestionScore> questionScoreSet) {
        this.questionScoreSet = questionScoreSet;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Paper getPaperOf() {
        return paperOf;
    }

    public void setPaperOf(Paper paperOf) {
        this.paperOf = paperOf;
    }
}
