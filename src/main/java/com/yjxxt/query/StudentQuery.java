package com.yjxxt.query;


import com.yjxxt.base.BaseQuery;

public class StudentQuery extends BaseQuery {

    private String studentName;

    private String cId;

    private String isValid;

    public StudentQuery() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    @Override
    public String toString() {
        return "StudentQuery{" +
                "studentName='" + studentName + '\'' +
                ", cId='" + cId + '\'' +
                ", isValid='" + isValid + '\'' +
                '}';
    }
}
