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
        private String studentName;
        private Long studentId;
        private String className;

        public StuInfo() {
        }

        public StuInfo(String studentName, Long studentId, String className) {
            this.studentName = studentName;
            this.studentId = studentId;
            this.className = className;
        }

        public String getStudentName() {
            return studentName;
        }

        public void setStudentName(String studentName) {
            this.studentName = studentName;
        }

        public Long getStudentId() {
            return studentId;
        }

        public void setStudentId(Long studentId) {
            this.studentId = studentId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
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
