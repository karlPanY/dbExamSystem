package com.exam.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by NeilHY on 2016/11/17.
 */
@Entity
public class StuClass implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "classId")
    private Long classId;

    private String className;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacherId",referencedColumnName = "teacherId")
    private Teacher teacher;

    @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Set<Student> studentSet;

    public StuClass() {
    }

    public StuClass(String className, Teacher teacher, Set<Student> studentSet) {
        this.className = className;
        this.teacher = teacher;
        this.studentSet = studentSet;
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
