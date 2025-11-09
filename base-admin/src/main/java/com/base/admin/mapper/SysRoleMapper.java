package com.base.admin.mapper;

import com.base.admin.entity.SysRole;
import com.base.admin.dto.SysRoleQueryDTO;
import java.util.List;

public interface SysRoleMapper {
    /**
     * 根据条件查询角色列表
     * @param roleQuery 查询条件
     * @return 角色列表
     */
    List<SysRole> findByCondition(SysRoleQueryDTO roleQuery);

    SysRole findById(Long id);

    int insert(SysRole role);

    int update(SysRole role);

    int deleteById(Long id);

    List<SysRole> findByRoleName(String roleName);
    
    /**
     * 根据角色名称精确查询角色
     * @param roleName 角色名称
     * @return 角色信息
     */
    SysRole findByRoleNameExact(String roleName);
}