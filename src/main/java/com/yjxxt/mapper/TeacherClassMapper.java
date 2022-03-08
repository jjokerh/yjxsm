package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.TeacherClass;


public interface TeacherClassMapper extends BaseMapper<TeacherClass,Integer> {

    TeacherClass selectByPrimaryKey(Integer id);


}