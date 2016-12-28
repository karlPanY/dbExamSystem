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
    private List<StudentGrade> rows;

    public GetStuGradeResponse() {
    }

    public GetStuGradeResponse(Integer total, List<StudentGrade> rows) {
        this.total = total;
        this.rows = rows;
    }

    public class StudentGrade{
        private String paper_name;
        private Float grade;
        private Integer rank;

        public StudentGrade() {
        }

        public StudentGrade(String paper_name, Float grade, Integer rank) {
            this.paper_name = paper_name;
            this.grade = grade;
            this.rank = rank;
        }

        public String getPaper_name() {
            return paper_name;
        }

        public void setPaper_name(String paper_name) {
            this.paper_name = paper_name;
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

    public List<StudentGrade> getRows() {
        return rows;
    }

    public void setRows(List<StudentGrade> rows) {
        this.rows = rows;
    }
}
