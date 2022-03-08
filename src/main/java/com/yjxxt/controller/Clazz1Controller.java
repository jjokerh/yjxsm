package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("clazz")
public class Clazz1Controller extends BaseController {

    @Autowired
    private ClazzService clazzService;

    //查询所有的班级
    @RequestMapping("findClazz")
    @ResponseBody
    public List<Map<String,Object>> findClazz(Integer clazzId){
        return clazzService.findClazz(clazzId);
    }


}
