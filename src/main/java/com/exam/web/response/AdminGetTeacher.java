package com.exam.web.response;

import com.exam.domain.StuClass;
import com.exam.domain.Teacher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by uio5 on 2016/12/25.
 */
public class AdminGetTeacher {
    public AdminGetTeacher(Set<Teacher>teacherSet) {

        total = teacherSet.size();
        Iterator iterator = teacherSet.iterator();
        while(iterator.hasNext())
        {
            Teacher teacher = (Teacher) iterator.next();
            Rows row = new Rows();
            Iterator classIte = teacher.getClassSet().iterator();
            if(classIte.hasNext())
                row.setClass_name(((StuClass)classIte.next()).getClassName());
            while(classIte.hasNext())
            {
                row.setClass_name(row.getClass_name()+","+((StuClass)classIte.next()).getClassName());
            }
            row.setPassword(teacher.getPassword());
            row.setTeacher_id(teacher.getId());
            row.setTeacher_name(teacher.getTeacherName());
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



            public long getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(long teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            String teacher_name;
            long teacher_id;
            String password;
            String class_name;

        }

}
