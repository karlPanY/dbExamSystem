package com.exam.web.response;

import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/12/22.
 */
public class GetClassStudents {
    private Integer total;
    private List<StuInfo> rows;

    public GetClassStudents() {
    }

    public GetClassStudents(Integer total, List<StuInfo> rows) {
        this.total = total;
        this.rows = rows;
    }

    public class StuInfo{
        private String student_name;
        private Long student_id;
        private String class_name;

        public StuInfo() {
        }

        public StuInfo(String student_name, Long student_id, String class_name) {
            this.student_name = student_name;
            this.student_id = student_id;
            this.class_name = class_name;
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

        public String getClass_name() {
            return class_name;
        }

        public void setClass_name(String class_name) {
            this.class_name = class_name;
        }
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<StuInfo> getRows() {
        return rows;
    }

    public void setRows(List<StuInfo> rows) {
        this.rows = rows;
    }
}
