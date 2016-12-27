package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/27.
 */
public class GetExactPaperAnswerToMark {
    private Long student_id;
    private String student_name;
    private List<paperAnswer> student_paper;
    private Float student_score;

    public GetExactPaperAnswerToMark() {
    }

    public GetExactPaperAnswerToMark(Long student_id, String student_name, List<paperAnswer> student_paper, Float student_score) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.student_paper = student_paper;
        this.student_score = student_score;
    }

    public class paperAnswer{
        private Long question_id;
        private String question_title;
        private String question_answer;
        private Float question_score;


        public paperAnswer() {
        }

        public paperAnswer(Long question_id, String question_title, String question_answer, Float question_score) {
            this.question_id = question_id;
            this.question_title = question_title;
            this.question_answer = question_answer;
            this.question_score = question_score;
        }

        public Long getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(Long question_id) {
            this.question_id = question_id;
        }

        public String getQuestion_title() {
            return question_title;
        }

        public void setQuestion_title(String question_title) {
            this.question_title = question_title;
        }

        public String getQuestion_answer() {
            return question_answer;
        }

        public void setQuestion_answer(String question_answer) {
            this.question_answer = question_answer;
        }

        public Float getQuestion_score() {
            return question_score;
        }

        public void setQuestion_score(Float question_score) {
            this.question_score = question_score;
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

    public List<paperAnswer> getStudent_paper() {
        return student_paper;
    }

    public void setStudent_paper(List<paperAnswer> student_paper) {
        this.student_paper = student_paper;
    }

    public Float getStudent_score() {
        return student_score;
    }

    public void setStudent_score(Float student_score) {
        this.student_score = student_score;
    }
}
