package com.yjxxt.service;

import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Permission;
import com.yjxxt.mapper.PermissionMapper;
import com.yjxxt.utils.AssertUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PermissionService extends BaseService<Permission,Integer> {

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 查询用户拥有的资源权限码
     * @param userId
     * @return
     */
    public List<String> queryUserHasRolesHasPermissions(Integer userId){
        return permissionMapper.selectUserHasRolesHasPermissions(userId);
    }

    /**
     * 查询用户拥有的资源权限码
     * @param userId
     * @return
     */
    public List<String> queryStudentHasRolesHasPermissions(Integer userId){
        return permissionMapper.selectStudentHasRolesHasPermissions(userId);
    }
}
