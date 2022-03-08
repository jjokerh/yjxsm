package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Clazz;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClazzMapper extends BaseMapper<Clazz,Integer> {

    Clazz selectClazzByName(String clazz);

    int countClazzNum(Integer clazzId);
    int deleteClazzByClazzId(Integer clazzId);

    //查询所有的班级
    @MapKey("")
    public List<Map<String ,Object>> selectClazz(Integer clazzId);
}