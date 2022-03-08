package com.yjxxt.service;

import com.yjxxt.base.BaseService;
import com.yjxxt.bean.Module;
import com.yjxxt.dto.TreeDto;
import com.yjxxt.mapper.ModuleMapper;
import com.yjxxt.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ModuleService extends BaseService<Module,Integer> {


    @Resource
    private ModuleMapper moduleMapper;

    @Resource
    private PermissionMapper permissionMapper;

    /**
     * 展示所有权限信息
     * @return
     */
    public List<TreeDto> findModules(){
        return moduleMapper.selectModules();
    }

    /**
     *  根据角色id查找modules
     * @param roleId
     * @return
     */
    public List<TreeDto> findModulesByRoleId(Integer roleId){
        List<TreeDto> tlist = moduleMapper.selectModules();
        List<Integer> roleHasMids = permissionMapper.selectModuleByRoleId(roleId);
        for (TreeDto treeDto : tlist) {
            if (roleHasMids.contains(treeDto.getId())) {
                treeDto.setChecked(true);
            }
        }
        return tlist;
    }
}
