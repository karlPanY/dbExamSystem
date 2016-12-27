package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/27.
 */
public class GetStudentScoreByPaperId {
    private Integer total;
    private List<studentInfo> rows;

    public GetStudentScoreByPaperId() {
    }

    public GetStudentScoreByPaperId(Integer total, List<studentInfo> rows) {
        this.total = total;
        this.rows = rows;
    }

    public class studentInfo{
        private String student_name;
        private Long student_id;
        private Float grade;
        private Integer rank;

        public studentInfo() {
        }

        public studentInfo(String student_name, Long student_id, Float grade, Integer rank) {
            this.student_name = student_name;
            this.student_id = student_id;
            this.grade = grade;
            this.rank = rank;
        }

        public String getStudent_name() {
            return student_name;
        }

        public void setStudent_name(String student_name) {
            this.student_name = student_name;
        }

        public Long getStudent_id() {
            return student_id;
        }

        public void setStudent_id(Long student_id) {
            this.student_id = student_id;
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

    public List<studentInfo> getRows() {
        return rows;
    }

    public void setRows(List<studentInfo> rows) {
        this.rows = rows;
    }
}
