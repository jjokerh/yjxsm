package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Student;
import com.yjxxt.bean.StudentStage;
import com.yjxxt.dto.StudentStageDto;
import com.yjxxt.mapper.StudentMapper;
import com.yjxxt.mapper.StudentStageMapper;
import com.yjxxt.query.StudentStageQuery;
import com.yjxxt.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentStageService extends BaseService<StudentStage,Integer> {
    @Resource
    private StudentStageMapper studentStageMapper;

    @Resource
    private StudentMapper studentMapper;

    //学生成绩模块——列表查询
    public Map<String,Object> findScoreBysId(StudentStageQuery studentStageQuery){
        //实例化map
        Map<String,Object> map=new HashMap<String, Object>();
        //初始化分页单位
        PageHelper.startPage(studentStageQuery.getPage(),studentStageQuery.getLimit());
        //开始分页
        PageInfo<StudentStage> plist=new PageInfo<StudentStage>(selectByParams(studentStageQuery));

        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());

        return map;
    }

    //学生成绩的评分系统
    public StudentStage selectStudentById(Integer id,String courseName){
        return studentStageMapper.selectStudentById(id,courseName);
    }

    public StudentStageDto selectStudentById2(Integer id){
        return studentStageMapper.selectStudentById2(id);
    }

    //成绩的添加
   /* @Transactional(propagation = Propagation.REQUIRED)
    public void changeStudentStage(StudentStage studentStage){
        System.out.println(studentStage);
        changeStudentStageScore(studentStage.getStudentId(),studentStage.getStageId(),studentStage.getScore());

        AssertUtil.isTrue(studentStageMapper.updateByPrimary(studentStage)<1,"添加失败");
    }*/

    //成绩的修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeStudentStageScore(StudentStage studentStage){
        //根据ID获取学生及对应的成绩信息
        StudentStage temp = (StudentStage) studentStageMapper.selectByPrimaryKey(studentStage.getId());
        //判断
        AssertUtil.isTrue(temp==null,"待修改记录不存在");
        //验证参数
        changeStudentStageScore(studentStage.getId(),studentStage.getStageId());
        //修改课程分数已经存在的问题
        StudentStage temp2 = studentStageMapper.selectScoreById(studentStage.getScore());
        AssertUtil.isTrue(temp2!=null && !(temp2.getId().equals(studentStage.getId())),"成绩已经存在");
        //设定默认值
        studentStage.setUpdateDate(new Date());
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(studentStage)<1,"修改失败了");
    }

    private void changeStudentStageScore(Integer id, Integer stageId) {
        //一·验证
        //学生ID非空
        AssertUtil.isTrue(id==null,"学生成绩ID不能为空");
        //课程ID
        AssertUtil.isTrue(stageId==null,"课程ID不能为空");
        //学生成绩
        //AssertUtil.isTrue(score==null,"学生成绩不能为空");
    }
}
