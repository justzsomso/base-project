package com.base.admin.service;

import com.base.admin.entity.SysRole;
import com.base.admin.util.PageResult;
import com.base.admin.dto.SysRoleQueryDTO;
import java.util.List;

public interface SysRoleService {
    /**
     * 根据ID查询角色
     * @param id 角色ID
     * @return 角色信息
     */
    SysRole findById(Long id);
    
    /**
     * 保存角色信息
     * @param role 角色对象
     * @return 是否保存成功
     */
    boolean save(SysRole role);
    
    /**
     * 更新角色信息
     * @param role 角色对象
     * @return 是否更新成功
     */
    boolean update(SysRole role);
    
    /**
     * 根据ID删除角色
     * @param id 角色ID
     * @return 是否删除成功
     */
    boolean deleteById(Long id);
    
    /**
     * 根据角色名称查询角色
     * @param roleName 角色名称
     * @return 角色列表
     */
    List<SysRole> findByRoleName(String roleName);
    
    /**
     * 根据条件分页查询角色
     * @param roleQuery 查询条件
     * @return 分页结果
     */
    PageResult<SysRole> findByCondition(SysRoleQueryDTO roleQuery);
}