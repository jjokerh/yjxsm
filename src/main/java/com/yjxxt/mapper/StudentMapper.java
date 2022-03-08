package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Student;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface StudentMapper extends BaseMapper<Student,Integer> {
    Integer deleteByPrimaryKey(Integer id);

    int insert(Student record);

    Integer insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(Student record);

    Integer updateByPrimaryKey(Student record);

    Student selectStudentByName(String studentName);

    Student queryStudentByStudentName(String sname);

    @MapKey("")
    List<Map<String,Object>> selectStudent();


}