package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/27.
 */
public class GetAllMarkedPapersInfo {
    private Long teacher_id;
    private String teacher_name;
    private List<paperInfo> data;

    public GetAllMarkedPapersInfo() {
    }

    public GetAllMarkedPapersInfo(Long teacher_id, String teacher_name, List<paperInfo> data) {
        this.teacher_id = teacher_id;
        this.teacher_name = teacher_name;
        this.data = data;
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

    public Long getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(Long teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getTeacher_name() {
        return teacher_name;
    }

    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
    }

    public List<paperInfo> getData() {
        return data;
    }

    public void setData(List<paperInfo> data) {
        this.data = data;
    }
}
