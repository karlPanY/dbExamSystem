package com.exam.web.request;

/**
 * Created by uio5 on 2016/12/23.
 */
public class AddClassRequest {

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public AddClassRequest() {
    }

    public AddClassRequest(String class_teacher, String class_name) {
        this.class_teacher = class_teacher;
        this.class_name = class_name;
    }

    public String getClass_teacher() {
        return class_teacher;
    }

    public void setClass_teacher(String class_teacher) {
        this.class_teacher = class_teacher;
    }

    private String class_teacher;
    private String class_name;
}
