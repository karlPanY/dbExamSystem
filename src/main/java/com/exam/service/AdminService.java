package com.exam.service;

import com.exam.domain.StuClass;
import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.web.response.AddClassResponse;

import java.util.Set;

/**
 * Created by NeilHY on 2016/11/15.
 */
public interface AdminService {
    Set<Teacher> getTeacher();//获得所有老师名单

    Set<StuClass> getstuClass();//获得所有班级名单

    StuClass getClassStu(long classid);//获得班级

    AddClassResponse addClass(StuClass stuClass);//添加班级

    Teacher getTeacherByName(String name);//根据名字获得老师

    boolean saveNewStudent(Student student);//添加学生

    boolean saveNewTeacher(Teacher teacher);//添加老师

    boolean updateStudent(Student student);//更新学生

    boolean updateTeacher(Teacher teacher, String[] classNames);//更新老师

    boolean deleteStudent(long student_id);//删除学生

    boolean deleteTeacher(long teacher_id);//删除老师
}
