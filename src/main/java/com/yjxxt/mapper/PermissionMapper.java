package com.yjxxt.mapper;

import com.yjxxt.base.BaseMapper;
import com.yjxxt.bean.Permission;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission,Integer> {

    List<String> selectUserHasRolesHasPermissions(Integer userId);

    List<Integer> selectModuleByRoleId(Integer roleId);

    int countPermissionById(Integer id);

    int deletePermissionById(Integer id);

    List<String> selectStudentHasRolesHasPermissions(Integer userId);
}