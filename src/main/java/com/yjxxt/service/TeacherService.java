package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Teacher;
import com.yjxxt.bean.TeacherClass;
import com.yjxxt.bean.TeacherRole;
import com.yjxxt.mapper.TeacherClassMapper;
import com.yjxxt.mapper.TeacherMapper;
import com.yjxxt.mapper.TeacherRoleMapper;
import com.yjxxt.model.UserModel;
import com.yjxxt.query.TeacherQuery;
import com.yjxxt.utils.AssertUtil;
import com.yjxxt.utils.Md5Util;
import com.yjxxt.utils.PhoneUtil;
import com.yjxxt.utils.UserIDBase64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

@Service
public class TeacherService extends BaseService<Teacher, Integer> {

    @Resource
    private TeacherMapper teacherMapper;

    @Autowired(required = false)
    private TeacherClassMapper teacherClassMapper;

    @Autowired(required = false)
    private TeacherRoleMapper teacherRoleMapper;

    /**
     * 用户登录
     * @param teaName
     * @param teaPwd
     * @return
     */
    public UserModel teacherLogin(String teaName, String teaPwd, String role) {
        //验证参数
        checkLoginParams(teaName, teaPwd);
        //根据用户名，查询用户对象
        Teacher teacher = teacherMapper.queryTeacherByTeacherName(teaName);
        System.out.println(teacher);
        //判断用户是否存在
        AssertUtil.isTrue(null==teacher,"用户不存在或已注销！");
        //检查权限
        System.out.println(role);
        AssertUtil.isTrue(!Objects.equals(role, teacher.getRoleName()),"你没有权限登录！");
        //检查密码
        checkLoginPwd(teaPwd, teacher.getTeacherPwd());
        //密码正确
        return buildUserInfo(teacher);
    }

    /**
     * 构建返回的用户信息
     * @param teacher
     * @return
     */
    private UserModel buildUserInfo(Teacher teacher) {
        UserModel userModel = new UserModel();
        userModel.setUserIdStr(UserIDBase64.encoderUserID(teacher.getId()));
        userModel.setUserName(teacher.getTeacherName());
        userModel.setRole(teacher.getRoleName());
        return userModel;
    }

    /**
     * 验证用户密码
     * @param teaPwd
     * @param realPwd
     */
    private void checkLoginPwd(String teaPwd, String realPwd) {
        teaPwd = Md5Util.encode(teaPwd);
        AssertUtil.isTrue(!realPwd.equals(teaPwd),"用户密码不正确！");
    }


    private void checkLoginParams(String teaName, String teaPwd) {
        AssertUtil.isTrue(StringUtils.isBlank(teaName),"用户姓名不能为空！");
        AssertUtil.isTrue(StringUtils.isBlank(teaPwd),"用户密码不能为空");
        //验证权限是否一致

    }


    /**教师模块
     * 教师条件查询
     *
     * @param teacherQuery
     * @return
     */
    public Map<String,Object> querySaleChanceByParams(TeacherQuery teacherQuery){
        //实例
        Map<String,Object> map=new HashMap<>();
        //实例化分页单位         页码                    每页显示多少行
        PageHelper.startPage(teacherQuery.getPage(),teacherQuery.getLimit());
        //开始分页
        PageInfo<Teacher> plist=new PageInfo<Teacher>(selectByParams(teacherQuery));
        //准备数据
        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());
        //返回
        return map;
    }

    /**
     * 添加
     *一，验证：
     * 1.用户名非空
     * 2.邮箱非空
     * 3.手机号非空
     *二，设定默认值
     * is_valid=1
     * createDate 系统时间
     * updateDate（修改）系统时间
     * 密码：
     *     123456---加密
     *三，添加是否成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void addTeacher(Teacher teacher){
        //验证
        checkTeacher(teacher);
        //设定默认值
        teacher.setIsValid(1);
        teacher.setCreateDate(new Date());
        teacher.setUpdateDate(new Date());
        //密码加密
        teacher.setTeacherPwd(Md5Util.encode("123456"));
        System.out.println(teacher.getJob());
        System.out.println(teacher.getRclass());
        //验证是否成功
        AssertUtil.isTrue(insertHasKey(teacher)<1,"添加失败");
        System.out.println(teacher.getId());
        //给中间表添加老师id和班级id完成老师分配班级
        TeacherClass c =new TeacherClass();
        c.setTeacherId(teacher.getId());
        c.setClazzId(Integer.parseInt(teacher.getRclass()));
        c.setCreateDate(new Date());
        c.setUpdateDate(new Date());
        AssertUtil.isTrue(teacherMapper.insertclass(c)<1,"添加失败");

        //给中间表添加老师id和职位id完成老师分配职位
        TeacherRole role = new TeacherRole();
        role.setTeacherId(teacher.getId());
        role.setRoleId(Integer.parseInt(teacher.getJob()));
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(teacherMapper.insertrole(role)<1,"添加失败");

    }
    /**
     *一，验证：
     *  1.用户名非空
     *  2.邮箱非空
     *  3.手机号非空
     * @param teacher
     */
    public void checkTeacher(Teacher teacher){
        //用户名非空
        AssertUtil.isTrue(StringUtils.isBlank(teacher.getTeacherName()),"用户名不能为空");
        //用户名唯一
        Teacher temp = teacherMapper.selectTeacherByName(teacher.getTeacherName());
        if (teacher.getId() == null) { // 添加操作
            // 判断是否存在
            AssertUtil.isTrue(temp != null, "用户名已被使用，请重新输入！");
        } else {
            // 更新操作 (数据存在，且id不是当前数据本身)
            AssertUtil.isTrue(temp != null && !temp.getId().equals(teacher.getId()), "用户名已被使用，请重新输入！");
        }
        // 邮箱非空
        AssertUtil.isTrue(StringUtils.isBlank(teacher.getEmail()),"邮箱不能为空");
        //手机号非空
        AssertUtil.isTrue(StringUtils.isBlank(teacher.getPhone()),"手机号不能为空");
        AssertUtil.isTrue(!PhoneUtil.isMobile(teacher.getPhone()),"手机号格式不合法");
    }


    /**用户管理
     * 一，修改：
     * 当前用户id必须存在，否则不能修改
     *  1.用户名非空，且唯一
     *  2.邮箱非空
     *  3.手机号非空，格式正确
     * 二，设定默认值
     *  is_valid=1
     *  updateDate （修改）系统时间
     * 三，添加是否成功
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void changeTeacher(Teacher teacher){
        //根据id获取用户信息
        Teacher temp = teacherMapper.selectByPrimaryKey(teacher.getId());
        //判断
        AssertUtil.isTrue(temp==null,"待修改的记录不存在");
        //验证
        checkTeacher(teacher);
        //设定默认值
        teacher.setUpdateDate(new Date());
        //判断是否成功
        System.out.println(teacher.getId());
        System.out.println(teacher.getTeacherName());
        AssertUtil.isTrue(updateByPrimaryKeySelective(teacher)<1,"修改失败");

        //修改教师分配的班级（判定是否在添加教师基本信息时分配了教师班级，如果分配过了班级就做修改操作，如果没有分配就做添加操作）
        if (teacherClassMapper.selectByPrimaryKey(teacher.getId())==null){
            //给中间表添加老师id和班级id完成老师分配班级
            TeacherClass c =new TeacherClass();
            c.setTeacherId(teacher.getId());
            c.setClazzId(Integer.parseInt(teacher.getRclass()));
            c.setCreateDate(new Date());
            c.setUpdateDate(new Date());
            AssertUtil.isTrue(teacherMapper.insertclass(c)<1,"修改教师班级失败");
        }
        TeacherClass ts = new TeacherClass();
        ts.setUpdateDate(new Date());
        ts.setTeacherId(teacher.getId());
        ts.setId(teacher.getId());
        ts.setClazzId(Integer.parseInt(teacher.getRclass()));
        AssertUtil.isTrue(teacherMapper.updateByTeacherClass(ts)<1,"修改教师班级失败");

        //修改教师分配的职位（判定是否在添加教师基本信息时分配了职位，如果分配过了职位就做修改操作，如果没有分配就做添加操作）
        if (teacherRoleMapper.selectByPrimaryKey(teacher.getId())==null){
            //给中间表添加老师id和职位id完成老师分配职位
            TeacherRole role = new TeacherRole();
            role.setTeacherId(teacher.getId());
            role.setRoleId(Integer.parseInt(teacher.getJob()));
            role.setCreateDate(new Date());
            role.setUpdateDate(new Date());
            AssertUtil.isTrue(teacherMapper.insertrole(role)<1,"修改教师职位失败");
        }

        TeacherRole role = new TeacherRole();
        role.setUpdateDate(new Date());
        role.setTeacherId(teacher.getId());
        role.setRoleId(Integer.parseInt(teacher.getJob()));
        AssertUtil.isTrue(teacherMapper.updateByTeacherRole(role)<1,"修改教师职位失败");

    }

    /**教师模块
     * 教师信息删除
     */
    @Transactional(propagation = Propagation.REQUIRED)//事务
    public void removeSaleChanceIds(Integer[] ids){
        System.out.println(Arrays.toString(ids));
        //验证
        AssertUtil.isTrue(ids== null || ids.length==0,"请选择要删除的数据");
        //判断对否成功
        AssertUtil.isTrue(deleteBatch(ids)!=ids.length,"删除失败了");

        //删除老师带的把班级中间表信息
        for (int i=0;i< ids.length;i++) {
            if (teacherClassMapper.selectByPrimaryKey(ids[i])!=null){
                AssertUtil.isTrue(teacherMapper.deleteTeacherClass((ids[i]))<1,"删除失败了");
            }
        }
        //删除老师的职位信息中间表信息
        for (int i=0;i< ids.length;i++) {
            if (teacherRoleMapper.selectByPrimaryKey((ids[i]))!=null) {
                AssertUtil.isTrue(teacherMapper.deleteTeacherRole((ids[i]))<1,"删除失败了");
            }
        }


    }


    /**查询职位
     *
     * @return
     */
    public List<Map<String,Object>> queryAllRoles(){
        return teacherMapper.queryAllRoles();
    }

    /**查询班级
     *
     * @return
     */
    public List<Map<String,Object>> queryAllClazz(){
        return teacherMapper.queryAllClazz();
    }
}
