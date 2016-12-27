package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/27.
 */
public class GetStudentIdListForMark {
    private Long paper_id;
    private List<studentNameAndId> student_id_list;

    public GetStudentIdListForMark() {
    }

    public GetStudentIdListForMark(Long paper_id, List<studentNameAndId> student_id_list) {
        this.paper_id = paper_id;
        this.student_id_list = student_id_list;
    }

    public class studentNameAndId{
        private Long student_id;
        private String student_name;

        public studentNameAndId() {
        }

        public studentNameAndId(Long student_id, String student_name) {
            this.student_id = student_id;
            this.student_name = student_name;
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
    }
    public Long getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Long paper_id) {
        this.paper_id = paper_id;
    }

    public List<studentNameAndId> getStudent_id_list() {
        return student_id_list;
    }

    public void setStudent_id_list(List<studentNameAndId> student_id_list) {
        this.student_id_list = student_id_list;
    }
}
