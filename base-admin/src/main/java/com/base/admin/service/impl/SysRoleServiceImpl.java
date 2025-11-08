package com.base.admin.service.impl;

import com.base.admin.entity.SysRole;
import com.base.admin.mapper.SysRoleMapper;
import com.base.admin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {
    
    @Autowired
    private SysRoleMapper sysRoleMapper;
    
    /**
     * 查询所有角色
     * @return 角色列表
     */
    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }
    
    /**
     * 根据ID查询角色
     * @param id 角色ID
     * @return 角色信息
     */
    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.findById(id);
    }
    
    /**
     * 保存角色信息
     * @param role 角色对象
     * @return 是否保存成功
     */
    @Override
    public boolean save(SysRole role) {
        return sysRoleMapper.insert(role) > 0;
    }
    
    /**
     * 更新角色信息
     * @param role 角色对象
     * @return 是否更新成功
     */
    @Override
    public boolean update(SysRole role) {
        return sysRoleMapper.update(role) > 0;
    }
    
    /**
     * 根据ID删除角色
     * @param id 角色ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteById(Long id) {
        return sysRoleMapper.deleteById(id) > 0;
    }
    
    /**
     * 根据角色名称查询角色
     * @param roleName 角色名称
     * @return 角色列表
     */
    @Override
    public List<SysRole> findByRoleName(String roleName) {
        return sysRoleMapper.findByRoleName(roleName);
    }
}