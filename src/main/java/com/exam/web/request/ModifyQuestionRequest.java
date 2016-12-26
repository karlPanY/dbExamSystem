package com.exam.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by NeilHY on 2016/12/26.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ModifyQuestionRequest implements Serializable{
    @JsonProperty("questionList")
    private questionInfo[] questionList;

    @JsonCreator
    public ModifyQuestionRequest() {
    }

    @JsonCreator
    public ModifyQuestionRequest(@JsonProperty("questionList")questionInfo[] questionList) {
        this.questionList = questionList;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class questionInfo implements Serializable{
        @JsonProperty("question_id")
        private Long question_id;
        @JsonProperty("question_answer")
        private String question_answer;
        @JsonProperty("question_score")
        private Float question_score;
        @JsonProperty("question_title")
        private String question_title;
        @JsonProperty("type")
        private String type;

        @JsonCreator
        public questionInfo() {
        }
        @JsonCreator
        public questionInfo(@JsonProperty("question_id")Long question_id, @JsonProperty("question_answer")String question_answer, @JsonProperty("question_score")Float question_score, @JsonProperty("question_title")String question_title, @JsonProperty("type")String type) {
            this.question_id = question_id;
            this.question_answer = question_answer;
            this.question_score = question_score;
            this.question_title = question_title;
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Long getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(Long question_id) {
            this.question_id = question_id;
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

        public String getQuestion_title() {
            return question_title;
        }

        public void setQuestion_title(String question_title) {
            this.question_title = question_title;
        }
    }

    public questionInfo[] getQuestionList() {
        return questionList;
    }

    public void setQuestionList(questionInfo[] questionList) {
        this.questionList = questionList;
    }
}
