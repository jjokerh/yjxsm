package com.yjxxt.query;

import com.yjxxt.base.BaseQuery;

public class StageQuery extends BaseQuery {

    private Integer id;
    private String stageName;
    private String teacherName;

    public StageQuery() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "StageQuery{" +
                "id=" + id +
                ", stageName='" + stageName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                '}';
    }
}
