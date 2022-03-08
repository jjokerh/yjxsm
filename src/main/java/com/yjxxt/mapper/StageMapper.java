package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Stage;
import com.yjxxt.query.StageQuery;
import org.apache.ibatis.annotations.MapKey;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StageMapper extends BaseMapper<Stage,Integer> {

    //查询所有阶段课程
    @MapKey("")
    public List<Map<String,Object>> selectStage(Integer stageId);

    //查询所有课程
    List<Stage> selectAllStage(StageQuery query);
    //将t_stage表中的信息选择出来
    @MapKey("")
    List<Map<String, Object>> selectStage();

    //根据课程名查课程
    Integer selectStageByName(String stageName);

    //插入选择老师
    Integer insertTeacherStage(Integer StageId, Integer teacherId, Date createDate, Date updateDate);

    //查询所有老师
    @MapKey("")
    List<Map<String, Object>> selectAllTeacher(Integer clazzId);

    //更新课程老师
    Integer updateTeacher(Integer id, Integer gettId, Date date);

    //名称查找
    Integer selectStageName(String stageName);

    //删除
    Integer deleteBatch(Integer[] ids);


    //查找学生
    @MapKey("")
    List<Map<String,Object>> findSC();

    @MapKey("")
    List<Map<String, Object>> selectStudentStage();

    @MapKey("")
    List<Map<String, Object>> findAll();


    Stage selectAllStageTeacher(Integer id);
}