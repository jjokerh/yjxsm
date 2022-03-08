package com.yjxxt.model;

public class TeacherModel {

    private String teacherName;
    private String idStr;

    public TeacherModel() {
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    @Override
    public String toString() {
        return "TeacherModel{" +
                "teacherName='" + teacherName + '\'' +
                ", idStr='" + idStr + '\'' +
                '}';
    }
}
