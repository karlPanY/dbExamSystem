package com.exam.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by uio5 on 2016/12/27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentUpload implements Serializable {

    @JsonProperty("paper_id")
    long paper_id;

    @JsonCreator
    public StudentUpload(@JsonProperty("paper_id")long paper_id,@JsonProperty("student_id") long student_id, @JsonProperty("answer")ArrayList<Answer> answer) {
        this.paper_id = paper_id;
        this.student_id = student_id;
        this.answer = answer;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public long getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(long paper_id) {
        this.paper_id = paper_id;
    }
    @JsonProperty("student_id")
    long student_id;

    public ArrayList<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(ArrayList<Answer> answer) {
        this.answer = answer;
    }

    @JsonProperty("answer")
    ArrayList<Answer> answer;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Answer implements Serializable{
        @JsonCreator
        public Answer( @JsonProperty("answer_text")String answer_text,  @JsonProperty("answer_id")long answer_id) {
            this.answer_text = answer_text;
            this.answer_id = answer_id;
        }

        public long getAnswer_id() {
            return answer_id;
        }

        public void setAnswer_id(long answer_id) {
            this.answer_id = answer_id;
        }

        public String getAnswer_text() {
            return answer_text;
        }

        public void setAnswer_text(String answer_text) {
            this.answer_text = answer_text;
        }

        @JsonProperty("answer_id")
        long answer_id;
        @JsonProperty("answer_text")
        String answer_text;
    }
}
