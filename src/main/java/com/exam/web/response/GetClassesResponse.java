package com.exam.web.response;

import com.exam.domain.StuClass;
import com.exam.domain.Teacher;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by NeilHY on 2016/12/22.
 */
public class GetClassesResponse {
    private Long teacherId;
    private String teacherName;
    private List<SimpleClass> classList;

    public GetClassesResponse() {
    }

    public GetClassesResponse(Teacher teacher) {
        this.teacherId = teacher.getId();
        this.teacherName = teacher.getTeacherName();
        this.classList = simplifyClass(teacher.getClassSet());
    }

    private List<SimpleClass> simplifyClass(Set<StuClass> originClass){
        Iterator<StuClass> iterator=originClass.iterator();
        List<SimpleClass> simpleClassList = new ArrayList<>();
        SimpleClass simpleClass;
        while (iterator.hasNext()){
            simpleClass= new SimpleClass(iterator.next().getClassId(), iterator.next().getClassName());
            simpleClassList.add(simpleClass);
        }
        return simpleClassList;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public List<SimpleClass> getClassList() {
        return classList;
    }

    public void setClassList(List<SimpleClass> classList) {
        this.classList = classList;
    }
    class SimpleClass{
        private Long classId;
        private String className;

        public SimpleClass() {
        }

        public SimpleClass(Long classId, String className) {
            this.classId = classId;
            this.className = className;
        }

        public Long getClassId() {
            return classId;
        }

        public void setClassId(Long classId) {
            this.classId = classId;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }
    }
}
