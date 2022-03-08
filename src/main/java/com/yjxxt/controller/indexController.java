package com.yjxxt.controller;

import com.yjxxt.base.BaseController;
import com.yjxxt.base.ResultInfo;
import com.yjxxt.bean.Student;
import com.yjxxt.bean.Teacher;
import com.yjxxt.model.UserModel;
import com.yjxxt.service.PermissionService;
import com.yjxxt.service.StudentService;
import com.yjxxt.service.TeacherService;
import com.yjxxt.utils.LoginUserUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class indexController extends BaseController {

    @Resource
    StudentService studentService;

    @Resource
    TeacherService teacherService;

    @Resource
    private PermissionService permissionService;

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("main")
    public String main(HttpServletRequest request) {
        int userId = LoginUserUtil.releaseUserIdFromCookie(request);
        String role = LoginUserUtil.releaseUserRoleFromCookie(request);
        UserModel user = new UserModel();
        switch (role) {
            case "student":
                Student student = studentService.selectByPrimaryKey(userId);
                user.setUserName(student.getStudentName());
                request.setAttribute("user",user);
                List<String> studentPermissions = new ArrayList<>();
                studentPermissions.add("50");
                studentPermissions.add("5020");
                studentPermissions.add("60");
                studentPermissions.add("6010");
                studentPermissions.add("6020");
                request.getSession().setAttribute("permissions",studentPermissions);
                break;
            case "teacher":
            case "headmaster":
            case "admin":
                Teacher teacher = teacherService.selectByPrimaryKey(userId);
                user.setUserName(teacher.getTeacherName());
                request.setAttribute("user",user);
                List<String> permissions = permissionService.queryUserHasRolesHasPermissions(userId);
                request.getSession().setAttribute("permissions",permissions);
                break;
        }

        return "main";
    }


    @RequestMapping("logout")
    @ResponseBody
    public ResultInfo logout() {
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setMsg("注销成功！");
        return resultInfo;
    }

}
