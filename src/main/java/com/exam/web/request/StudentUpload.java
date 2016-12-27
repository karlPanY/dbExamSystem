package com.exam.web.request;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by uio5 on 2016/12/27.
 */
public class StudentUpload {

    long paper_id;

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

    long student_id;

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    ArrayList<Answer>answers;

    public class Answer
    {
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

        long answer_id;
        String answer_text;
    }
}
