package com.exam.web.response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by NeilHY on 2016/12/23.
 */
public class GetAllPapers {
    private Long teacherId;
    private String teacherName;
    private List<PaperInfo> data;

    public GetAllPapers() {
    }

    public GetAllPapers(Long teacherId, String teacherName, List<PaperInfo> data) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.data = data;
    }

    public class PaperInfo {
        private Long paper_id;
        private String paper_name;
        private String paper_start;
        private String paper_end;
        private Integer paper_status;

        public PaperInfo(Long paper_id,String paper_name, Date paper_start, Date paper_end) {
            this.paper_id=paper_id;
            this.paper_name = paper_name;
            if (paper_start != null && paper_end != null) {
                this.paper_start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paper_start);
                this.paper_end =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(paper_end);
                this.paper_status=1;
            }else{
                this.paper_start ="";
                this.paper_end ="";
                this.paper_status=0;
            }


        }

        public PaperInfo() {
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

        public String getPaper_start() {
            return paper_start;
        }

        public void setPaper_start(String paper_start) {
            this.paper_start = paper_start;
        }

        public String getPaper_end() {
            return paper_end;
        }

        public void setPaper_end(String paper_end) {
            this.paper_end = paper_end;
        }

        public Integer getPaper_status() {
            return paper_status;
        }

        public void setPaper_status(Integer paper_status) {
            this.paper_status = paper_status;
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

    public List<PaperInfo> getData() {
        return data;
    }

    public void setData(List<PaperInfo> data) {
        this.data = data;
    }
}
