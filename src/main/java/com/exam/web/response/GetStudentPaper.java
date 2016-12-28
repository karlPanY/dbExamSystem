package com.exam.web.response;

import com.exam.domain.Question;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by uio5 on 2016/12/27.
 */
public class GetStudentPaper {
    private long paper_id;

    public GetStudentPaper(long paper_id, String paper_name, long student_id, String student_name, Set<Question> questions) {
        this.paper_id = paper_id;
        this.paper_name = paper_name;
        this.student_id = student_id;
        this.student_name = student_name;
        this.datas = new ArrayList<>();

        Iterator iterator = questions.iterator();
        while(iterator.hasNext())
        {
            Question question = (Question) iterator.next();
            Data data = new Data();
            data.setQuestion_id(question.getQuestionId());
            data.setQuestion_type(question.getType());
            data.setQuestion_title(question.getTitle());
            this.datas.add(data);
        }
    }

    public long getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(long paper_id) {
        this.paper_id = paper_id;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public ArrayList<Data> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<Data> datas) {
        this.datas = datas;
    }

    private String paper_name;
    private long student_id;
    private String student_name;
    private ArrayList<Data> datas;


    class Data
    {
        public long getQuestion_id() {
            return question_id;
        }

        public void setQuestion_id(long question_id) {
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

        private long question_id;
        private String question_type;
        private String question_title;
    }
}
