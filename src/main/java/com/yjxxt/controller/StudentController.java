package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.model.UserModel;
import com.yjxxt.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("student")
public class StudentController extends BaseController {

    @Resource
    private StudentService studentService;

    @PostMapping("login")
    @ResponseBody
    public ResultInfo studentLogin(String stuName, String stuPwd) {
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = studentService.studentLogin(stuName, stuPwd);
        resultInfo.setResult(userModel);
        return resultInfo;
    }
}
