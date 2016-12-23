package com.exam.web;


public class RequestUrls {
    public static final String LoginUrl = "/login";
    public static final String toLoginUrl = "/toLogin";

    public static final String HomeUrl = "/";
    public static final String teacherIndex = "/teacherIndex";
    public static final String getClassManagement = "/classManagement";
    public static final String getPaperManagement = "/paperManagement";
    public static final String getDetailGrade = "/detailGrade";
    public static final String getPaperMark = "/paperMark";

    public static final String getAllClasses = "/getAllClasses";
    public static final String getClassStu = "/getClassStu/{class_id}";
    public static final String getStuGrade= "/getStuGrade/{teacher_id}/{student_id}";


    public static final String admin = "/admin";

    public static final String exam = "/exam";
    public static final String select = "/select";
    public static final String toExam = "/toExam/{paperId}";

}
