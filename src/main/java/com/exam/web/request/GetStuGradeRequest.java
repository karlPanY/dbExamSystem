package com.exam.web.request;

/**
 * Created by NeilHY on 2016/12/22.
 */
public class GetStuGradeRequest {
    private Long teacher_id;
    private Long student_id;

    public GetStuGradeRequest() {
    }

    public GetStuGradeRequest(Long teacher_id, Long student_id) {
        this.teacher_id = teacher_id;
        this.student_id = student_id;
    }

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }
}
