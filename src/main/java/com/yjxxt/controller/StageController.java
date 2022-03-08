package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Stage;
import com.yjxxt.query.StageQuery;
import com.yjxxt.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("stage")
public class StageController extends BaseController {

    @Autowired
    private StageService stageService;

    //查询所有的阶段课程
    @RequestMapping("findStage")
    @ResponseBody
    public List<Map<String, Object>> findStage(Integer clazzId) {
        return stageService.findStage(clazzId);
    }

    @RequestMapping("index")
    public String index() {
        return "stage/course";
    }


    @RequestMapping("list")
    @ResponseBody
    public Map<String, Object> List(StageQuery query) {
        return stageService.findAllStage(query);
    }

    //将t_stage表中的信息选择出来
    @RequestMapping("sales")
    @ResponseBody
    public List<Map<String, Object>> Sales() {
        return stageService.findStage();
    }

    //添加或修改课程的前端页面跳转
    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(Integer id, Model model) {
        Stage stage = stageService.findById(id);
        System.out.println(stage);
        model.addAttribute("stage", stage);
        return "stage/add_update";
    }

    //添加课程的后端数据传输
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Stage stage) {
        stageService.addStage(stage);
        return success("添加成功");
    }

    //修改课程的后端数据传输
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Stage stage) {
        stageService.updateStage(stage);
        return success("修改成功");
    }

    //删除课程
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteCourse(Integer[] ids) {
        stageService.deleteByIds(ids);
        return success("删除成功");
    }

    //老师下拉框
    @RequestMapping("teacher")
    @ResponseBody
    public List<Map<String, Object>> findTeacher(Integer clazzId) {
        return stageService.findAllTeacher(clazzId);
    }

    //分析
    @RequestMapping("anal")
    public String anal() {
        return "stage/analysis";
    }

    //分析表中的学生信息
    @RequestMapping("findSC")
    @ResponseBody
    public List<Map<String, Object>> findSC() {
        return stageService.findSC();
    }

    @RequestMapping("findStuSta")
    @ResponseBody
    public List<Map<String, Object>> findStuSta() {
        return stageService.findStudentStage();
    }

    @RequestMapping("find")
    @ResponseBody
    public List<Map<String, Object>> find() {
        return stageService.findAll();
    }
}
