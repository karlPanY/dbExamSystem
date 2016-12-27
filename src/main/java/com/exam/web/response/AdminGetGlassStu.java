package com.exam.web.response;

import com.exam.domain.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by uio5 on 2016/12/25.
 */
public class AdminGetGlassStu {
    public AdminGetGlassStu(Set<Student>students ) {

        total = students.size();
        Iterator iterator = students.iterator();
        while(iterator.hasNext())
        {
            Student student = (Student) iterator.next();
            Rows row = new Rows();
            row.setClass_name(student.getStuClass().getClassName());
            row.setPassword(student.getPassword());
            row.setStudent_id(student.getId());
            row.setStudent_name(student.getStudentName());
            rows.add(row);
        }

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<Rows> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Rows> rows) {
        this.rows = rows;
    }

    int total = 0 ;

        ArrayList<Rows> rows = new ArrayList<>();

        class Rows
        {

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getClass_name() {
                return class_name;
            }

            public void setClass_name(String class_name) {
                this.class_name = class_name;
            }

            public String getStudent_name() {
                return student_name;
            }

            public void setStudent_name(String student_name) {
                this.student_name = student_name;
            }


            public long getStudent_id() {
                return student_id;
            }

            public void setStudent_id(long student_id) {
                this.student_id = student_id;
            }

            String student_name;
            long student_id;
            String password;
            String class_name;

        }

}
