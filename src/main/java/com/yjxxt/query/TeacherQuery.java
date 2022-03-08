package com.yjxxt.query;

import com.yjxxt.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherQuery extends BaseQuery {

    private String teacherName;
    private String sex;
    private Integer id;
    private String roleName;


}
