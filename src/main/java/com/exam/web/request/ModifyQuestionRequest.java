package com.exam.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by NeilHY on 2016/12/26.
 */
public class ModifyQuestionRequest implements Serializable{

        private Long question_id;
        private String question_answer;
        private Float question_score;
        private String question_title;
        private String type;

    public ModifyQuestionRequest() {
    }


    public ModifyQuestionRequest(Long question_id, String question_answer, Float question_score, String question_title, String type) {
        this.question_id = question_id;
        this.question_answer = question_answer;
        this.question_score = question_score;
        this.question_title = question_title;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
