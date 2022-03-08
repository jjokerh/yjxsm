package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Role;
import com.yjxxt.bean.Student;
import com.yjxxt.bean.Teacher;
import com.yjxxt.model.UserModel;
import com.yjxxt.query.RoleQuery;
import com.yjxxt.service.RoleService;
import com.yjxxt.service.StudentService;
import com.yjxxt.service.TeacherService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController{

    @Autowired
    private RoleService roleService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    /**
     *  跳转到角色管理页面
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "role/role";
    }


    /**
     *  展示角色信息
     * @param roleQuery
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Map<String,Object> list(RoleQuery roleQuery){
        return roleService.findAllRoleByParam(roleQuery);
    }

    /**
     *  判断是更新还是添加页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("addOrUpdateRolePage")
    public String addOrUpdateRolePage(Integer id, Model model){
        if(null!=id){
            model.addAttribute("role",roleService.selectByPrimaryKey(id));
        }
        return "role/add_update";
    }

    /**
     * 添加角色信息
     * @param role
     * @return
     */
    @RequestMapping("add")
    @ResponseBody
    public ResultInfo saveRole(Role role){
        roleService.saveRole(role);
        return success("添加成功了！");
    }

    /**
     *  更新角色信息
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public ResultInfo updateRole(Role role){
        roleService.updateRole(role);
        return success("更新成功了！");
    }

    /**
     *  删除角色信息
     * @param role
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public ResultInfo deleteRole(Role role){
        roleService.deleteRole(role);
        return success("删除更改了！");
    }

    /**
     * 跳转到角色授权页面
     * @return
     */
    @RequestMapping("toAddGrantPage")
    public String toAddGrantPage(Integer id,Model model){
        model.addAttribute("id",id);
        return "role/grant1";
    }

    /**
     * 给角色添加权限
     * @param mids
     * @param id
     * @return
     */
    @RequestMapping("addGrant")
    @ResponseBody
    public ResultInfo addGrant(Integer[] mids,Integer id){
        roleService.addGrant(mids,id);
        return success("权限添加成功！");
    }

    /**
     * 跳转到修改密码页面
     * @return
     */
    @RequestMapping("toPasswordPage")
    public String toPasswordPage(){
        return "role/password";
    }

    /**
     * 修改密码操作
     * @return
     */
    @RequestMapping("updatePwd")
    @ResponseBody
    public ResultInfo updatePwd(HttpServletRequest request,String oldPassword,String newPassword,String confirmPassword){
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        String role = LoginUserUtil.releaseUserRoleFromCookie(request);
//        UserModel user = new UserModel();
        switch (role) {
            case "student":
//                Student student = studentService.selectByPrimaryKey(userId);
                roleService.changeStudentPwd(userId,oldPassword,newPassword,confirmPassword);
                break;
            case "teacher":
            case "headmaster":
            case "admin":
//                Teacher teacher = teacherService.selectByPrimaryKey(userId);
//                user.setUserName(teacher.getTeacherName());
//                request.setAttribute("user",user);
//                List<String> permissions = permissionService.queryUserHasRolesHasPermissions(userId);
//                request.getSession().setAttribute("permissions",permissions);

                roleService.changeTeacherPwd(userId,oldPassword,newPassword,confirmPassword);
                break;
        }
        return  success("修改密码成功！");
    }

}
