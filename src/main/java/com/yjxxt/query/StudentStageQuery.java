package com.yjxxt.query;

import com.yjxxt.base.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentStageQuery extends BaseQuery {
    private Integer sId;

    private Integer stId;

    private Integer score;

    private String studentName;

    private Integer cId;

    private Integer stageId;
}

