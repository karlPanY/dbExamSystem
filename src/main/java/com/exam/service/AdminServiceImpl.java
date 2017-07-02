package com.exam.service;

import com.exam.domain.Paper;
import com.exam.domain.StuClass;
import com.exam.domain.Student;
import com.exam.domain.Teacher;
import com.exam.domain.dao.*;
import com.exam.web.response.AddClassResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by uio5 on 2016/11/19.
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ClassRepository classRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private PaperRepository paperRepository;

    @Override
    public Set<Teacher> getTeacher() {

        Set set = new HashSet<>(teacherRepository.findAll());
        return set;
    }

    @Override
    public Set<StuClass> getstuClass() {
        Set set = new HashSet<>(classRepository.findAll());
        return set;
    }

    @Override
    public StuClass getClassStu(long classid) {
        StuClass stuClass = classRepository.findOne(classid);
        return stuClass;
    }

    @Override
    public AddClassResponse addClass(StuClass stuClass) {
        AddClassResponse addClassResponse = new AddClassResponse();
        try {
            Teacher teacher=teacherRepository.findOne(stuClass.getTeacher().getId());
            teacher.getClassSet().add(stuClass);
            teacherRepository.save(teacher);
            teacherRepository.flush();
            stuClass = classRepository.findByClassName(stuClass.getClassName());
            addClassResponse.setSuccess(true);
            addClassResponse.setClass_id(stuClass.getClassId());
            return addClassResponse;
        }
        catch (Exception e)
        {
            return addClassResponse;
        }

    }

    @Override
    public Teacher getTeacherByid(long teacher_id) {
        return teacherRepository.findOne(teacher_id);
    }

    @Override
    public boolean saveNewStudent(Student student) {
        if(studentRepository.findOne(student.getId())!=null)
            return false;
        else
        {
            StuClass stuClass=classRepository.findOne(student.getStuClass().getClassId());
            stuClass.getStudentSet().add(student);
            classRepository.save(stuClass);
            return true;
        }
    }
    @Override
    public boolean saveNewTeacher(Teacher teacher, String[] classNames) {
        Teacher teacher1 = teacherRepository.findOne(teacher.getId());
        if(teacher1!=null)
            return false;
        else
        {
                Set<StuClass> new_class_set = new HashSet<>();
                for (int i = 0; i < classNames.length; i++) {
                    if (classNames[i]=="")
                        break;
                    StuClass stuClass = classRepository.findByClassName(classNames[i]);
                    Teacher beforeTeacher = stuClass.getTeacher();
                    if (beforeTeacher != null) {
                        beforeTeacher.getClassSet().remove(stuClass);
                        teacherRepository.save(beforeTeacher);
                    }
                    teacher.setClassSet(new_class_set);
                    teacherRepository.save(teacher);
                    stuClass.setTeacher(teacher);
                    classRepository.save(stuClass);
                    new_class_set.add(stuClass);
                }
                teacher.setClassSet(new_class_set);
                teacherRepository.save(teacher);
                return true;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        Student student1 = studentRepository.findOne(student.getId());
        if(student1==null)
            return false;
        else
        {
            student1.setStudentName(student.getStudentName());
            student1.setPassword(student.getPassword());
            studentRepository.save(student1);
            return true;
        }
    }

    @Override
    public boolean updateTeacher(Teacher teacher, String[] classNames) {
        Teacher teacher1 = teacherRepository.findOne(teacher.getId());
        if(teacher1==null)
            return false;
        else
        {
            teacher1.setPassword(teacher.getPassword());
            teacher1.setTeacherName(teacher.getTeacherName());
            Set<StuClass> new_class_set = new HashSet<>();
            for (int i =0 ; i<classNames.length;i++)
            {
                StuClass stuClass = classRepository.findByClassName(classNames[i]);
                Teacher beforeTeacher=stuClass.getTeacher();
                if(beforeTeacher!=null) {
                    beforeTeacher.getClassSet().remove(stuClass);
                    teacherRepository.save(beforeTeacher);
                }
                stuClass.setTeacher(teacher1);
                classRepository.save(stuClass);
                new_class_set.add(stuClass);
            }
            teacher1.setClassSet(new_class_set);
            teacherRepository.save(teacher1);
            return true;
        }
    }

    @Override
    public boolean deleteStudent(long student_id) {
        Student student1 = studentRepository.findOne(student_id);
        if(student1==null)
            return false;
        else {
            StuClass stuClass = student1.getStuClass();
            stuClass.getStudentSet().remove(student1);
            classRepository.save(stuClass);
            studentRepository.delete(student_id);
            return true;
        }
    }

    @Override
    public boolean deleteTeacher(long teacher_id) {
        Teacher teacher1 = teacherRepository.findOne(teacher_id);
        if(teacher1==null)
            return false;
        else {
            Set<StuClass> classSet = teacher1.getClassSet();
            Iterator iterator = classSet.iterator();
            while(iterator.hasNext())
            {
                StuClass stuClass = (StuClass) iterator.next();
                stuClass.setTeacher(null);
                classRepository.save(stuClass);
            }
            Set<Paper>paperset=teacher1.getPaperSet();
            iterator = paperset.iterator();
            while(iterator.hasNext())
            {
                Paper paper = (Paper) iterator.next();
                paper.setTeacherOf(null);
                paperRepository.save(paper);
            }

            teacher1.setClassSet(null);
            teacher1.setPaperSet(null);
            teacher1.setQuestionSet(null);
            teacherRepository.save(teacher1);
            teacherRepository.delete(teacher_id);
            return true;
        }
    }

}
