package com.exam.web.response;

import java.util.List;

/**
 * Created by NeilHY on 2016/12/26.
 */
public class GetPaperContent {
    private Integer total;
    private List<QuestionInfo> questionInfoList;

    public GetPaperContent() {
    }

    public GetPaperContent(Integer total, List<QuestionInfo> questionInfoList) {
        this.total = total;
        this.questionInfoList = questionInfoList;
    }

    public class QuestionInfo{
        private Long question_id;
        private String question_type;
        private String question_title;
        private Float question_score;
        private String question_answer;

        public QuestionInfo() {
        }

        public QuestionInfo(Long question_id, String question_type, String question_title, Float question_score, String question_answer) {
            this.question_id = question_id;
            this.question_type = question_type;
            this.question_title = question_title;
            this.question_score = question_score;
            this.question_answer = question_answer;
        }

        public Long getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(Long question_id) {
            this.question_id = question_id;
        }

        public String getQuestion_type() {
            return question_type;
        }

        public void setQuestion_type(String question_type) {
            this.question_type = question_type;
        }

        public String getQuestion_title() {
            return question_title;
        }

        public void setQuestion_title(String question_title) {
            this.question_title = question_title;
        }

        public Float getQuestion_score() {
            return question_score;
        }

        public void setQuestion_score(Float question_score) {
            this.question_score = question_score;
        }

        public String getQuestion_answer() {
            return question_answer;
        }

        public void setQuestion_answer(String question_answer) {
            this.question_answer = question_answer;
        }
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<QuestionInfo> getQuestionInfoList() {
        return questionInfoList;
    }

    public void setQuestionInfoList(List<QuestionInfo> questionInfoList) {
        this.questionInfoList = questionInfoList;
    }
}
