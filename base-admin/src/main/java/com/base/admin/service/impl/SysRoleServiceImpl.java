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
    
    @Override
    public List<SysRole> findAll() {
        return sysRoleMapper.findAll();
    }
    
    @Override
    public SysRole findById(Long id) {
        return sysRoleMapper.findById(id);
    }
    
    @Override
    public boolean save(SysRole role) {
        return sysRoleMapper.insert(role) > 0;
    }
    
    @Override
    public boolean update(SysRole role) {
        return sysRoleMapper.update(role) > 0;
    }
    
    @Override
    public boolean deleteById(Long id) {
        return sysRoleMapper.deleteById(id) > 0;
    }
    
    @Override
    public List<SysRole> findByRoleName(String roleName) {
        return sysRoleMapper.findByRoleName(roleName);
    }
}