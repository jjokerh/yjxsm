package com.yjxxt.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Permission;
import com.yjxxt.bean.Role;
import com.yjxxt.bean.Student;
import com.yjxxt.bean.Teacher;
import com.yjxxt.mapper.*;
import com.yjxxt.model.UserModel;
import com.yjxxt.query.RoleQuery;
import com.yjxxt.utils.AssertUtil;
import com.yjxxt.utils.Md5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class RoleService extends BaseService<Role,Integer> {

    @Resource
    private RoleMapper roleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private StudentMapper studentMapper;

    @Resource
    private TeacherMapper teacherMapper;

    /**
     *  展示所有角色信息
     * @param roleQuery
     * @return
     */
    public Map<String,Object> findAllRoleByParam(RoleQuery roleQuery){
        Map<String,Object> map = new HashMap<>();
        PageHelper.startPage(roleQuery.getPage(),roleQuery.getLimit());
        PageInfo<Role> plist =new PageInfo<>(roleMapper.selectByParams(roleQuery));

        map.put("code",0);
        map.put("msg","success");
        map.put("count",plist.getTotal());
        map.put("data",plist.getList());

        return map;
    }

    /**
     *  插入角色信息
     * @param role
     */
    public void saveRole(Role role){
        AssertUtil.isTrue(StringUtils.isBlank(role.getRoleName()),"请输入用户名！");
        Role temp = roleMapper.selectRoleByName(role.getRoleName());
        AssertUtil.isTrue(null!=temp,"该角色已存在！");
        role.setIsValid(1);
        role.setCreateDate(new Date());
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(roleMapper.insertHasKey(role)<1,"添加角色失败！");
    }

    /**
     *  更新角色信息
     * @param role
     */
    public void updateRole(Role role){
        Role temp1 = roleMapper.selectByPrimaryKey(role.getId());
        AssertUtil.isTrue(null==temp1,"待修改的角色不存在！");
        Role temp2 = roleMapper.selectRoleByName(role.getRoleName());
        AssertUtil.isTrue(null!=temp2 && !(temp2.getId().equals(role.getId())),"该角色已重复！");
        role.setUpdateDate(new Date());
        AssertUtil.isTrue(updateByPrimaryKeySelective(role)<1,"修改角色失败！");
    }

    /**
     * 删除角色信息
     * @param role
     */
    public void deleteRole(Role role){
        AssertUtil.isTrue(role.getId()==null || selectByPrimaryKey(role.getId())==null,"请选择要删除的角色！");
        role.setIsValid(0);
        AssertUtil.isTrue(roleMapper.updateByPrimaryKeySelective(role)<1,"删除角色失败了！");
    }

    /**
     *  授予角色权限
     * @param mids
     * @param id
     */
    public void addGrant(Integer[] mids,Integer id){
        Role temp = roleMapper.selectByPrimaryKey(id);
        AssertUtil.isTrue(null==temp||null==id,"待授权角色不存在！");
        //统计当前角色原来有多少权限
        int count = permissionMapper.countPermissionById(id);
        if(count>0){
            //删除原有的权限
            int temp2 = permissionMapper.deletePermissionById(id);
            AssertUtil.isTrue(temp2!=count,"权限分配失败！");
        }
        //存放信息
        if(null!=mids && mids.length>0){
            List<Permission> plist = new ArrayList<>();
            for (Integer mid : mids) {
                Permission permission = new Permission();
                permission.setId(id);
                permission.setModuleId(mid);
                permission.setUpdateDate(new Date());
                permission.setAclValue(moduleMapper.selectByPrimaryKey(mid).getOptValue());
                plist.add(permission);
            }
            AssertUtil.isTrue(permissionMapper.insertBatch(plist)!=plist.size(),"授权失败！");
        }
    }

    /**
     *  修改学生密码
     * @param sId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    public void changeStudentPwd(Integer sId,String oldPassword,String newPassword,String confirmPassword){
        //通过sql获取user对象
        Student student = studentMapper.selectByPrimaryKey(sId);
        //参数的校验、创建一个新的方法
        checkStudentPasswordParams(student,oldPassword,newPassword,confirmPassword);
        //设置新密码
        student.setStudentPwd(Md5Util.encode(newPassword));
        //更新成功
        AssertUtil.isTrue(studentMapper.updateByPrimaryKeySelective(student)<1,"修改密码失败");

    }

    /**
     *  学生校验密码的参数
     * @param student
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkStudentPasswordParams(Student student, String oldPassword, String newPassword, String confirmPassword) {
        //用户对象验证
        AssertUtil.isTrue(null == student,"用户名未登录或不存在");
        //原始密码非空验证
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原始密码不能为空");
        //原始密码正确验证
        AssertUtil.isTrue(!(student.getStudentPwd().equals(Md5Util.encode(oldPassword))),"原始密码不正确");
        //新密码非空验证
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        //新密码和原始密码相等判断
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码不能和原始密码相等");
        //确认密码非空验证
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"确认密码不能为空");
        //确认密码和新密码相等判断
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)),"确认密码和新密码不相等");

    }

    /**
     *  修改老师密码
     * @param tId
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    public void changeTeacherPwd(Integer tId,String oldPassword,String newPassword,String confirmPassword){
        //通过sql获取user对象
        Teacher teacher = teacherMapper.selectByPrimaryKey(tId);
        //参数的校验、创建一个新的方法
        checkTeacherPasswordParams(teacher,oldPassword,newPassword,confirmPassword);
        //设置新密码
        teacher.setTeacherPwd(Md5Util.encode(newPassword));
        //更新成功
        AssertUtil.isTrue(teacherMapper.updateByPrimaryKeySelective(teacher)<1,"修改密码失败");

    }

    /**
     *  老师校验密码的参数
     * @param teacher
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    private void checkTeacherPasswordParams(Teacher teacher, String oldPassword, String newPassword, String confirmPassword) {
        //用户对象验证
        AssertUtil.isTrue(null == teacher,"用户名未登录或不存在");
        //原始密码非空验证
        AssertUtil.isTrue(StringUtils.isBlank(oldPassword),"原始密码不能为空");
        //原始密码正确验证
        AssertUtil.isTrue(!(teacher.getTeacherPwd().equals(Md5Util.encode(oldPassword))),"原始密码不正确");
        //新密码非空验证
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"新密码不能为空");
        //新密码和原始密码相等判断
        AssertUtil.isTrue(oldPassword.equals(newPassword),"新密码不能和原始密码相等");
        //确认密码非空验证
        AssertUtil.isTrue(StringUtils.isBlank(newPassword),"确认密码不能为空");
        //确认密码和新密码相等判断
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)),"确认密码和新密码不相等");

    }

}
