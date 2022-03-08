package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Clazz;
import com.yjxxt.mapper.ClazzMapper;
import com.yjxxt.query.ClazzQuery;
import com.yjxxt.utils.AssertUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author:34456
 * @date:2022/1/5
 * @description
 */
@Service
public class ClazzService extends BaseService<Clazz,Integer> {
    @Resource
    private ClazzMapper clazzMapper;

    /**
     *
     * @param clazzQuery
     * @return
     */
    public Map<String, Object> queryClazzByParams(ClazzQuery clazzQuery) {
        //实例化map
        Map<String, Object> map = new HashMap<String, Object>();
        //开启分页
        PageHelper.startPage(clazzQuery.getPage(), clazzQuery.getLimit());
        //自动分页
        PageInfo<Clazz> pageInfo = new PageInfo<Clazz>(clazzMapper.selectByParams(clazzQuery));
        map.put("code",0);
        map.put("msg", "success");
        map.put("count", pageInfo.getTotal());
        map.put("data", pageInfo.getList());
        return map;
    }


    /**
     *添加
     * @param clazz
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addclazz(Clazz clazz) {
        //验证
        checkClazz( clazz.getClazzName(), clazz.getCampus());
        //用户名唯一
        Clazz temp = clazzMapper.selectClazzByName(clazz.getClazzName());
        AssertUtil.isTrue(temp != null, "班级名已经存在");
        //设定默认
        clazz.setIsValid(1);
        clazz.setCreateDate(new Date());
        clazz.setUpdateDate(new Date());
        //验证是否成功
        //AssertUtil.isTrue(insertSelective(user) < 1, "添加失败了");
        AssertUtil.isTrue(insertHasKey(clazz) < 1, "添加失败了");
        System.out.println(clazz.getId()+"<<<");
    }

    private void checkClazz( String clazzName, String campus) {
        AssertUtil.isTrue(StringUtils.isBlank(clazzName), "班级名不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(campus), "校区不能为空");

    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void changeClazz(Clazz clazz) {
        //根据ID获取用户的信息
        Clazz temp = clazzMapper.selectByPrimaryKey(clazz.getId());
        //判断
        AssertUtil.isTrue(temp == null, "待修改的记录不存在");
        //验证参数
        checkClazz(clazz.getClazzName(), clazz.getCampus());
        //修改用户名已经存在问题
        Clazz temp2 = clazzMapper.selectClazzByName(clazz.getClazzName());
        AssertUtil.isTrue(temp2 != null && !(temp2.getId().equals(clazz.getId())), "班级名称已经存在");
        //设定默认值
        clazz.setUpdateDate(new Date());
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(clazz) < 1, "修改失败了");
    }

    /**
     *
     * @param ids
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeClazzIds(Integer[] ids){
        //验证
        AssertUtil.isTrue(ids==null|| ids.length==0,"请选择删除数据");


        //判断删除成功与否
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"删除失败了");
    }


    //查询所有的班级
    public List<Map<String,Object>> findClazz(Integer clazzId){
        return clazzMapper.selectClazz(clazzId);
    }
}
