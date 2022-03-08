package com.yjxxt.query;

import com.yjxxt.base.BaseQuery;

/**
 * @author:34456
 * @date:2022/1/5
 * @description
 */
public class ClazzQuery extends BaseQuery {
    private Integer id;

    private String clazzName;
    private String num;

    private String campus;
    private Integer isValid;

    public Integer getIsValid() {
        return isValid;
    }

    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public ClazzQuery() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

}