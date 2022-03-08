package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Student;
import com.yjxxt.query.StudentQuery;
import com.yjxxt.service.ClazzService;
import com.yjxxt.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("student")
public class Student1Controller extends BaseController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ClazzService clazzService;



    //学生管理模块列表
    @RequestMapping("list")
    @ResponseBody
    public Map<String ,Object> list(StudentQuery studentQuery){

        return studentService.findStudentByParams(studentQuery);
    }

    //学生管理模块的前端显示
    @RequestMapping("index")
    public String index(){
        return "student/student";
    }


    //学生模块——前端增加更新
    @RequestMapping("addOrUpdatePage")
    public String addPage(Integer id, Model model){
        if (id!=null){
            Student student=studentService.selectByPrimaryKey(id);
            model.addAttribute("student",student);
        }
        return "student/add_update";
    }

    //学生模块——添加
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo add(Student student){
        //用户的添加
        studentService.addStudent(student);
        //返回目标对象
        return success("学生添加成功了");
    }

    //学生模块——修改
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Student student){
        //学生的添加
        studentService.changeStudent(student);
        //返回目标对象
        return success("学生修改成功了");
    }

    //学生模块——批量删除
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo delete(Integer[] ids){
        //用户的删除
        studentService.removeStudentIds(ids);
        //返回目标对象
        return success("批量删除成功了");
    }

    //学生模块——下拉框
    @RequestMapping("find")
    @ResponseBody
    public List<Map<String,Object>> findClass(Integer clazzId){
        return clazzService.findClazz(clazzId);
    }

    //成绩模块下拉框——班级搜索
    //查询所有的班级
    @RequestMapping("findClazz")
    @ResponseBody
    public List<Map<String,Object>> findClazz(Integer clazzId){
        return clazzService.findClazz(clazzId);
    }
}
