package com.base.admin.service;

import com.base.admin.entity.SysRole;
import java.util.List;

public interface SysRoleService {
    List<SysRole> findAll();
    SysRole findById(Long id);
    boolean save(SysRole role);
    boolean update(SysRole role);
    boolean deleteById(Long id);
    List<SysRole> findByRoleName(String roleName);
}