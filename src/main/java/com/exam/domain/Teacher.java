package com.exam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/13.
 */
@Entity
public class Teacher implements Serializable {
    @Id
    @Column(name = "teacherId")
    private Long id;


    @Column(nullable = false,unique = true)
    private String teacherName;

    @Column(nullable = false)
    private String password;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private Set<Question> questionSet;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.REMOVE,CascadeType.MERGE})
    private Set<Paper> paperSet;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<StuClass> classSet;

    public Teacher() {
    }

    public Teacher(Long id, String teacherName, String password, Set<Question> questionSet, Set<Paper> paperSet, Set<StuClass> classSet) {
        this.id = id;
        this.teacherName = teacherName;
        this.password = password;
        this.questionSet = questionSet;
        this.paperSet = paperSet;
        this.classSet = classSet;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Set<StuClass> getClassSet() {
        return classSet;
    }

    public void setClassSet(Set<StuClass> classSet) {
        this.classSet = classSet;
    }

    public Long getId() {
        return id;
    }


    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public Set<Paper> getPaperSet() {
        return paperSet;
    }

    public void setPaperSet(Set<Paper> paperSet) {
        this.paperSet = paperSet;
    }

}
