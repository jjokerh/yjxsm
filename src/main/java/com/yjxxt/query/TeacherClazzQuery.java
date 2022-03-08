package com.yjxxt.query;

import com.yjxxt.base.BaseQuery;

public class TeacherClazzQuery extends BaseQuery {

    private String teacherName;
    private String clazzName;

    public TeacherClazzQuery() {
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    @Override
    public String toString() {
        return "TeacherClazzQuery{" +
                "teacherName='" + teacherName + '\'' +
                ", clazzName='" + clazzName + '\'' +
                '}';
    }

}
