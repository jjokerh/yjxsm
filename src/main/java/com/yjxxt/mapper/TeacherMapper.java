package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Teacher;
import com.yjxxt.bean.TeacherClass;
import com.yjxxt.bean.TeacherRole;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface TeacherMapper extends BaseMapper<Teacher,Integer> {
    Integer deleteByPrimaryKey(Integer id);

    int insert(Teacher record);

    Integer insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Integer id);

    Integer updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    Teacher queryTeacherByTeacherName(String teaName);

    //根据用户名查询用户对象
    Teacher selectTeacherByName(String teacherName);

    // 查询所有职位
    @MapKey("id")
    List<Map<String, Object>> queryAllRoles();

    //查询班级
    @MapKey("id")
    List<Map<String,Object>> queryAllClazz();

    //添加老师分配班级
    int insertclass(TeacherClass teacherClass);

    //添加老师分配职位
    int insertrole(TeacherRole teacherRole);

    //修改老师分配的班级
    int updateByTeacherClass(TeacherClass teacherClass);

    //修改老师分配的职位
    int updateByTeacherRole(TeacherRole teacherRole);

    //删除老师分配的班级
    Integer deleteTeacherClass(Integer teacherId);

    //删除老师分配的职位
    Integer deleteTeacherRole(Integer teacherId);
}