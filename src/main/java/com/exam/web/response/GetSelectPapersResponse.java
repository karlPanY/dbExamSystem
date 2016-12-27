package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/27.
 */
public class GetSelectPapersResponse {
    private Long student_id;
    private String student_name;
    private List<papersSelectInfo> paper_info_list;

    public GetSelectPapersResponse() {
    }

    public GetSelectPapersResponse(Long student_id, String student_name, List<papersSelectInfo> paper_info_list) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.paper_info_list = paper_info_list;
    }

    public class papersSelectInfo{
        private Long paper_id;
        private String paper_name;
        private String paper_start;
        private String paper_end;

        public papersSelectInfo() {
        }

        public papersSelectInfo(Long paper_id, String paper_name, String paper_start, String paper_end) {
            this.paper_id = paper_id;
            this.paper_name = paper_name;
            this.paper_start = paper_start;
            this.paper_end = paper_end;
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
    }

    public Long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public List<papersSelectInfo> getPaper_info_list() {
        return paper_info_list;
    }

    public void setPaper_info_list(List<papersSelectInfo> paper_info_list) {
        this.paper_info_list = paper_info_list;
    }
}
