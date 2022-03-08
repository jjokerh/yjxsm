package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.StudentStage;
import com.yjxxt.dto.StudentStageDto;
import org.apache.ibatis.annotations.Param;

public interface StudentStageMapper extends BaseMapper<StudentStage,Integer> {
    //根据ID查询学生的ID和课程信息
    StudentStage selectStudentById(@Param("id") Integer id,@Param("courseName") String courseName);


    StudentStageDto selectStudentById2(@Param("id") Integer id);

    //修改学生分数——评分
    Integer updateByPrimary(StudentStage studentStage);

    //修改学生分数
    StudentStage selectScoreById(Integer id);

}