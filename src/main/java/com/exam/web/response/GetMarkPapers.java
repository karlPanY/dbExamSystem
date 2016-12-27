package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/26.
 */
public class GetMarkPapers {
    private Long teacherId;
    private String teacherName;
    private List<papersMarkInfo> papersMarkInfoList;

    public GetMarkPapers() {
    }

    public GetMarkPapers(Long teacherId, String teacherName, List<papersMarkInfo> papersMarkInfoList) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.papersMarkInfoList = papersMarkInfoList;
    }

    public class papersMarkInfo{
        private Long paper_id;
        private String paper_name;

        public papersMarkInfo() {
        }

        public papersMarkInfo(Long paper_id, String paper_name) {
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

    public List<papersMarkInfo> getPapersMarkInfoList() {
        return papersMarkInfoList;
    }

    public void setPapersMarkInfoList(List<papersMarkInfo> papersMarkInfoList) {
        this.papersMarkInfoList = papersMarkInfoList;
    }
}
