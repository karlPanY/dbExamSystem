package com.exam.web.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by NeilHY on 2016/12/28.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePaper implements Serializable{
    @JsonProperty("paperName")
    private String paperName;
    @JsonProperty("paperStart")
    private String paperStart;
    @JsonProperty("paperEnd")
    private String paperEnd;
    @JsonProperty("paperContentList")
    private List<paperContent> paperContentList;
    @JsonCreator
    public CreatePaper() {
    }
    @JsonCreator
    public CreatePaper(@JsonProperty("paperName")String paperName, @JsonProperty("paperStart")String paperStart,@JsonProperty("paperEnd") String paperEnd, @JsonProperty("paperContentList")List<paperContent> paperContentList) {
        this.paperName = paperName;
        this.paperStart = paperStart;
        this.paperEnd = paperEnd;
        this.paperContentList = paperContentList;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class paperContent implements Serializable {
        @JsonProperty("questionTitle")
        private String questionTitle;
        @JsonProperty("questionType")
        private String questionType;
        @JsonProperty("questionAnswer")
        private String questionAnswer;
        @JsonProperty("questionScore")
        private Float questionScore;
        @JsonCreator
        public paperContent() {
        }
        @JsonCreator
        public paperContent( @JsonProperty("questionTitle")String questionTitle, @JsonProperty("questionType")String questionType,@JsonProperty("questionAnswer") String questionAnswer, @JsonProperty("questionScore")Float questionScore) {
            this.questionTitle = questionTitle;
            this.questionType = questionType;
            this.questionAnswer = questionAnswer;
            this.questionScore = questionScore;
        }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(String questionAnswer) {
        this.questionAnswer = questionAnswer;
    }

    public Float getQuestionScore() {
        return questionScore;
    }

    public void setQuestionScore(Float questionScore) {
        this.questionScore = questionScore;
    }
}

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperStart() {
        return paperStart;
    }

    public void setPaperStart(String paperStart) {
        this.paperStart = paperStart;
    }

    public String getPaperEnd() {
        return paperEnd;
    }

    public void setPaperEnd(String paperEnd) {
        this.paperEnd = paperEnd;
    }

    public List<paperContent> getPaperContentList() {
        return paperContentList;
    }

    public void setPaperContentList(List<paperContent> paperContentList) {
        this.paperContentList = paperContentList;
    }
}
