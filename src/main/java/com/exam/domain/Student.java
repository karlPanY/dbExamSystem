package com.exam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/14.
 */
@Entity
public class Student implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, unique = true)
    private Long studentId;

    @Column(nullable = false, length = 20)
    private String studentName;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "classId",referencedColumnName = "classId")
    private StuClass stuClass;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id.student",cascade = CascadeType.ALL)
    private Set<PaperScore> paperScoreSet;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "id.student",cascade = CascadeType.ALL)
    private Set<QuestionScore> questionScoreSet;

    public Student() {
    }

    public Student(Long studentId, String studentName, String password,  StuClass stuClass, Set<PaperScore> paperScoreSet, Set<QuestionScore> questionScoreSet) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.password = password;
        this.stuClass = stuClass;
        this.paperScoreSet = paperScoreSet;
        this.questionScoreSet = questionScoreSet;
    }

    public StuClass getStuClass() {
        return stuClass;
    }

    public void setStuClass(StuClass stuClass) {
        this.stuClass = stuClass;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QuestionScore> getQuestionScoreSet() {
        return questionScoreSet;
    }

    public void setQuestionScoreSet(Set<QuestionScore> questionScoreSet) {
        this.questionScoreSet = questionScoreSet;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PaperScore> getPaperScoreSet() {
        return paperScoreSet;
    }

    public void setPaperScoreSet(Set<PaperScore> paperScoreSet) {
        this.paperScoreSet = paperScoreSet;
    }
}
