package com.yjxxt.bean;

import java.util.Date;

public class StudentStage {
    private Integer id;

    private Integer studentId;

    private Integer stageId;

    private Date createDate;

    private Date updateDate;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStageId() {
        return stageId;
    }

    public void setStageId(Integer stageId) {
        this.stageId = stageId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "StudentStage{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", stageId=" + stageId +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", score=" + score +
                '}';
    }
}