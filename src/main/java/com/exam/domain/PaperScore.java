package com.exam.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by NeilHY on 2016/11/14.
 */
@Entity
public class PaperScore implements Serializable{
    @EmbeddedId
    private PaperScoreId id;

    private Float score;//学生得分

    public PaperScore() {
    }

    public PaperScore(PaperScoreId id, Float score) {
        this.id = id;
        this.score = score;
    }

    public PaperScoreId getId() {
        return id;
    }

    public void setId(PaperScoreId id) {
        this.id = id;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)return true;
        if(obj==null || getClass() != obj.getClass())return false;
        PaperScore that= (PaperScore) obj;
        if(getId()!=null ? !getId().equals(that.getId()) : that.getId() != null)return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (getId() != null ? getId().hashCode():0);
    }
}

