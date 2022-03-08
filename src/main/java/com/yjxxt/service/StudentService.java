package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Student;
import com.yjxxt.mapper.StudentMapper;
import com.yjxxt.model.UserModel;
import com.yjxxt.query.StudentQuery;
import com.yjxxt.utils.AssertUtil;
import com.yjxxt.utils.Md5Util;
import com.yjxxt.utils.PhoneUtil;
import com.yjxxt.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService extends BaseService<Student, Integer> {

    @Resource
    private StudentMapper studentMapper;

    /**
     * 用户登录
     * @param studentName
     * @param studentPwd
     * @return
     */
    public UserModel studentLogin(String studentName, String studentPwd) {
        //验证参数
        checkLoginParams(studentName, studentPwd);
        //根据用户名，查询用户对象
        Student student = studentMapper.queryStudentByStudentName(studentName);
        //判断用户是否存在
        AssertUtil.isTrue(null==student,"用户不存在或已注销！");
        //用户对象不为空
        checkLoginPwd(studentPwd, student.getStudentPwd());
        //密码正确
        return buildUserInfo(student);
    }

    /**
     * 构建返回的用户信息
     * @param student
     * @return
     */
    private UserModel buildUserInfo(Student student) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(student.getId()));
        userModel.setUserName(student.getStudentName());
        userModel.setRole("student");
        return userModel;
    }

    /**
     * 验证用户密码
     * @param stuPwd
     * @param realPwd
     */
    private void checkLoginPwd(String stuPwd, String realPwd) {
        stuPwd = Md5Util.encode(stuPwd);
        AssertUtil.isTrue(!realPwd.equals(stuPwd),"用户密码不正确！");
    }


    private void checkLoginParams(String stuName, String stuPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(stuName),"用户姓名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(stuPwd),"用户密码不能为空");
    }

    //查询所有的学生
    public List<Map<String,Object>> queryStudent(){
        return studentMapper.selectStudent();
    }

    //学生模块的列表查询
    public Map<String,Object> findStudentByParams(StudentQuery studentQuery){
        //实例化Map
        Map<String,Object> map = new HashMap<String ,Object>();
        //初始化分页单位
        PageHelper.startPage(studentQuery.getPage(),studentQuery.getLimit());
        //开始分页
        PageInfo<Student> plist = new PageInfo<Student>(selectByParams(studentQuery));

        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());

        return map;
    }

    //学生模块——添加功能实现
    @Transactional(propagation = Propagation.REQUIRED)
    public void addStudent(Student student){
        //验证
        //学生名，QQ，手机号非空
        checkStudent(student.getStudentName(),student.getQq(),student.getPhone());
        //学生名唯一
        Student temp = studentMapper.selectStudentByName(student.getStudentName());
        AssertUtil.isTrue(temp!=null,"用户名已经存在");

        //设定默认值
        student.setIsValid(1);
        student.setCreateDate(new Date());
        student.setUpdateDate(new Date());
        //密码进行加密
        student.setStudentPwd(Md5Util.encode("123456"));

        //判断添加是否成功
        AssertUtil.isTrue(insertSelective(student)<1,"添加失败了");
        //System.out.println(student.getId()+"<<<<<"+student.getcId());
        //批量添加——关联两张表

    }

    private void checkStudent(String studentName,String qq,String phone){
        //验证
        //学生名非空
        AssertUtil.isTrue(StringUtils.isBlank(studentName),"学生名字不能为空");

        //QQ非空
        AssertUtil.isTrue(StringUtils.isBlank(qq),"qq不能为空");

        //手机号非空
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(phone),"请输入合法的手机号");
    }

    //学生模块——修改
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeStudent(Student student){
        //根据ID获取学生信息
        Student temp = studentMapper.selectByPrimaryKey(student.getId());
        //判断
        AssertUtil.isTrue(temp == null, "待修改的记录不存在");
        //验证参数
        checkStudent(student.getStudentName(),student.getQq(),student.getPhone());
        //修改用户名已经存在的问题
        Student temp2 = studentMapper.selectStudentByName(student.getStudentName());
        AssertUtil.isTrue(temp2!=null && !(temp2.getId().equals(student.getId())),"用户名称已经存在");
        //设定默认值
        student.setUpdateDate(new Date());
        //判断修改是否成功
        AssertUtil.isTrue(updateByPrimaryKeySelective(student)<1,"修改失败了");

    }

    //批量删除
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeStudentIds(Integer[] ids){
        //验证
        AssertUtil.isTrue(ids == null || ids.length == 0,"请选择你要删除的项目");
 /*       //删除学生的班级信息
        for (Integer studentId : ids){
            //统计当前学生有多少
            int count = studentMapper.countStudentNum(studentId);
            //删除学生信息
            if (count>0){
                AssertUtil.isTrue(studentMapper.deleteStudentByStudentId(studentId)!=count,"学生批量删除失败");
            }
        }*/
        //判断是否删除成功
        AssertUtil.isTrue(deleteBatch(ids)<1,"删除失败");

    }
}
