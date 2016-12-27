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

    public static final String getAllPapers= "/getAllPapers";
    public static final String getPaperContent= "/getPaperContent/{paper_id}";
    public static final String setPaperTime= "/setPaperTime";
    public static final String changeQuestions= "/changeQuestions";
    public static final String getMarkPapers= "/getMarkPapers";
    public static final String getMarkingPaper= "/getMarkingPaper/{paper_id}/{student_id}";
    public static final String getStudentIdListForMark= "/getStudentIdListForMark/{paper_id}";
    public static final String setStudentNoneSelectScore= "/setStudentNoneSelectScore/{paper_id}/{student_id}/{score}";
    public static final String getAllMarkedPapers= "/getAllMarkedPapers";
    public static final String getStudentScoreByPaperId= "/getStudentScoreByPaperId/{paper_id}";


    public static final String admin = "/admin";

    public static final String exam = "/exam";
    public static final String select = "/select";
    public static final String toExam = "/toExam/{paperId}";
    public static final String getSelectPapers = "/getSelectPapers";

    public static  final String adminInit = "/admin/init";
    public static final String addClass = "/admin/addClass";
    public static final String adminGetClassStu = "/admin/getClassStu";
    public static final String adminGetTea = "/admin/getTeacher";

    public static final String adminAddStudent = "/admin/addStudent";
    public static final String adminAddTeacher = "/admin/addTeacher";

    public static final String adminDeleteStudent="/admin/deleteStudent";
    public static final String adminDeleteTeacher="/admin/deleteTeacher";

}
