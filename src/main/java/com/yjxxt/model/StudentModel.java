package com.yjxxt.model;

public class StudentModel {

    private String studentName;
    private String idStr;

    public StudentModel() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getIdStr() {
        return idStr;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    @Override
    public String toString() {
        return "StudentModel{" +
                "studentName='" + studentName + '\'' +
                ", idStr='" + idStr + '\'' +
                '}';
    }
}
