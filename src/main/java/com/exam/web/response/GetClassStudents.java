package com.exam.web.response;

import com.exam.domain.Student;

import java.util.Set;

/**
 * Created by NeilHY on 2016/12/22.
 */
public class GetClassStudents {
    private Integer total;
    private Set<Student> rows;

    public GetClassStudents() {
    }

    public GetClassStudents(Integer total, Set<Student> rows) {
        this.total = total;
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Set<Student> getRows() {
        return rows;
    }

    public void setRows(Set<Student> rows) {
        this.rows = rows;
    }
}
