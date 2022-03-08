package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.TeacherRole;

public interface TeacherRoleMapper extends BaseMapper<TeacherRole,Integer> {

    TeacherRole selectByPrimaryKey(Integer id);

}