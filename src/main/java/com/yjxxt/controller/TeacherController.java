package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Teacher;
import com.yjxxt.model.UserModel;
import com.yjxxt.query.TeacherQuery;
import com.yjxxt.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("teacher")
public class TeacherController extends BaseController {

    @Resource
    private TeacherService teacherService;

    @RequestMapping("login")
    @ResponseBody
    public ResultInfo teacherLogin(String teaName, String teaPwd, String role) {
        ResultInfo resultInfo = new ResultInfo();
        UserModel userModel = teacherService.teacherLogin(teaName, teaPwd, role);
        resultInfo.setResult(userModel);
        return resultInfo;
    }


    /**教师信息模块
     * 跳转至教师信息页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "teacher/index";
    }

    /**教师信息模块
     * 显示教师信息录入操作页面
     * @return
     */
    @RequestMapping("addOrUpdateDialog")
    public String addOrUpdateDialog(){
        return "teacher/add_update";
    }


    /**教师信息模块
     * 条件查找
     *
     * @param teacherQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> saylist(TeacherQuery teacherQuery){
        //
        System.out.println(teacherQuery);
        Map<String, Object> map = teacherService.querySaleChanceByParams(teacherQuery);
        //map转json
        return map;

    }

    /**教师信息模块
     *  添加教师
     * @param teacher
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ResultInfo save(Teacher teacher){
        ResultInfo resultInfo = new ResultInfo();
        //修改信息
        teacherService.addTeacher(teacher);
        //返回目标数据对象
        return success("教师信息添加成功");
    }

    /**教师信息模块
     * 修改教师信息
     * @param teacher
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo update(Teacher teacher){
        ResultInfo resultInfo = new ResultInfo();
        //用户的修改
        teacherService.changeTeacher(teacher);
        //返回目标数据对象
        return success("教师信息修改成功");
    }

    /**教师信息模块
     *
     * 回显修改窗口教师信息
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateSaleChancePage")
    public String addOrUpdateSaleChancePage(Integer id, Model model){
        System.out.println(id+"hhhhhhhhhh");
        if (null!=id){
            //通过主键查询老师数据
            Teacher teacher = teacherService.selectByPrimaryKey(id);
            model.addAttribute("teacher",teacher);
            System.out.println(teacher);
        }
        return "teacher/add_update";
    }


    /**教师信息模块
     * 删除教师信息
     * @param ids
     * @return
     */
    @RequestMapping("dels")
    @ResponseBody
    public ResultInfo deletes(Integer[] ids){
        //操作
        teacherService.removeSaleChanceIds(ids);
        //返回目标对象
        return success("教师信息删除成功了");
    }


    @RequestMapping("queryAllRoles")
    @ResponseBody
    public List<Map<String,Object>> queryAllRoles(){
        return teacherService.queryAllRoles();
    }

    @RequestMapping("queryAllClazz")
    @ResponseBody
    public List<Map<String,Object>> queryAllClazz(){
        return teacherService.queryAllClazz();
    }
}
