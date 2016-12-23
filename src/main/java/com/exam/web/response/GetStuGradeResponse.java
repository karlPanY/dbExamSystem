package com.exam.web.response;

import com.exam.domain.PaperScore;
import com.exam.domain.Student;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/12/22.
 */
public class GetStuGradeResponse {
    private Integer total;
    private List<StudentGrade> studentGradeList;

    public GetStuGradeResponse() {
    }

    public GetStuGradeResponse(Integer total, List<StudentGrade> studentGradeList) {
        this.total = total;
        this.studentGradeList = studentGradeList;
    }

    public GetStuGradeResponse(Student student) {
        Set<PaperScore> paperScoreSet= student.getPaperScoreSet();
        this.total=paperScoreSet.size();
        this.studentGradeList = getStudentGrades(paperScoreSet);
    }

    private List<StudentGrade> getStudentGrades(Set<PaperScore> paperScores) {
        Iterator iterator=paperScores.iterator();

        return null;
    }

    public class StudentGrade{
        private String papar_name;
        private Float grade;
        private Integer rank;

        public StudentGrade() {
        }

        public StudentGrade(String papar_name, Float grade, Integer rank) {
            this.papar_name = papar_name;
            this.grade = grade;
            this.rank = rank;
        }

        public String getPapar_name() {
            return papar_name;
        }

        public void setPapar_name(String papar_name) {
            this.papar_name = papar_name;
        }

        public Float getGrade() {
            return grade;
        }

        public void setGrade(Float grade) {
            this.grade = grade;
        }

        public Integer getRank() {
            return rank;
        }

        public void setRank(Integer rank) {
            this.rank = rank;
        }
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<StudentGrade> getStudentGradeList() {
        return studentGradeList;
    }

    public void setStudentGradeList(List<StudentGrade> studentGradeList) {
        this.studentGradeList = studentGradeList;
    }
}
