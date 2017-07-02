package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/27.
 */
public class GetAllMarkedPapersInfo {
    private Long teacherId;

    public GetAllMarkedPapersInfo(Long teacherId, String teacherName, List<paperInfo> papersMarkInfoList) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.papersMarkInfoList = papersMarkInfoList;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    private String teacherName;

    public List<paperInfo> getPapersMarkInfoList() {
        return papersMarkInfoList;
    }

    public void setPapersMarkInfoList(List<paperInfo> papersMarkInfoList) {
        this.papersMarkInfoList = papersMarkInfoList;
    }

    private List<paperInfo> papersMarkInfoList;

    public GetAllMarkedPapersInfo() {
    }



    public class paperInfo{
        private Long paper_id;
        private String paper_name;

        public paperInfo() {
        }

        public paperInfo(Long paper_id, String paper_name) {
            this.paper_id = paper_id;
            this.paper_name = paper_name;
        }

        public Long getPaper_id() {
            return paper_id;
        }

        public void setPaper_id(Long paper_id) {
            this.paper_id = paper_id;
        }

        public String getPaper_name() {
            return paper_name;
        }

        public void setPaper_name(String paper_name) {
            this.paper_name = paper_name;
        }
    }


}
