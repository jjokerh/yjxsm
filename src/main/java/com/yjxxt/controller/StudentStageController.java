package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Student;
import com.yjxxt.bean.StudentStage;
import com.yjxxt.dto.StudentStageDto;
import com.yjxxt.query.StudentStageQuery;
import com.yjxxt.service.StudentStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("studentStage")
public class StudentStageController extends BaseController {
    @Autowired
    private StudentStageService studentStageService;

    //学生模块阶段成绩——前端
    @RequestMapping("index")
    public String index2(){
        return "student/score";
    }

    //学生模块搜索——前端
    @RequestMapping("find")
    @ResponseBody
    public Map<String,Object> score(StudentStageQuery studentStageQuery){
        System.out.println(studentStageQuery);
        return studentStageService.findScoreBysId(studentStageQuery);
    }

    //学生模成绩评分——前端
    @RequestMapping("scorePage")
    public String scorePage(Integer id, Model model){
        if (id!=null){
            StudentStageDto temp = studentStageService.selectStudentById2(id);
            model.addAttribute("studentStageDto",temp);
        }
        return "student/score_update";
    }

    //评分系统——成绩的添加
  /*  @RequestMapping("addScore")
    @ResponseBody
    public ResultInfo changeStudentStage(StudentStage studentStage){
        studentStageService.changeStudentStage(studentStage);
        return success("成功");
    }*/

//    @RequestMapping("huixian")
//    @ResponseBody
//    public StudentStageDto selectStatByStuId(HttpServletRequest req,StudentStage studentStage) {
//        StudentStageDto temp = studentStageService.selectStudentById2(studentStage.getId());
//        System.out.println(temp);
//        req.setAttribute("studendStageDto",temp);
//        return temp;
//    }
    //评分系统——成绩的修改
    @RequestMapping("updateScore")
    @ResponseBody
    public ResultInfo updateScore(StudentStage studentStage){
        //学生成绩的改变
        studentStageService.changeStudentStageScore(studentStage);
        //返回目标对象
        return success("学生成绩修改成功");
    }

}
