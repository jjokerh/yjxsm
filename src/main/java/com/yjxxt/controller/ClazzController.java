package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Clazz;
import com.yjxxt.query.ClazzQuery;
import com.yjxxt.service.ClazzService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author:34456
 * @date:2022/1/5
 * @description
 */
@Controller
@RequestMapping("customer_serve")
public class ClazzController extends BaseController{
    @Resource
    private ClazzService clazzService;



    /**
     * 多条件分页查询
     * @param query
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> queryClazzByParams (ClazzQuery query) {
        return clazzService.queryClazzByParams(query);
    }

    /**
     * 进入班级管理页面
     * @return
     */
    @RequestMapping("index/1")
    public String index () {
        return "clazz/course_inquiry";
    }

    @RequestMapping("addCourse")
    public String addCourse(Integer id, Model model){
        if(id!=null){
            Clazz clazz=clazzService.selectByPrimaryKey(id);
            model.addAttribute("clazz",clazz);
        }
        return "clazz/course_update";
    }
    //用户的添加
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Clazz clazz){
        //用户的添加
        clazzService.addclazz(clazz);
        //返回目标对象
        return success("添加ok");
    }
    //用户的修改
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Clazz clazz){
        //用户的添加
        clazzService.changeClazz(clazz);
        return success("修改ok");
    }

    //批量删除
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        clazzService.removeClazzIds(ids);
        return success("批量删除用户ok");
    }
}
