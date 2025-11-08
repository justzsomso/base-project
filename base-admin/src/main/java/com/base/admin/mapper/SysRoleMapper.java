package com.base.admin.mapper;

import com.base.admin.entity.SysRole;
import java.util.List;

public interface SysRoleMapper {
    List<SysRole> findAll();

    SysRole findById(Long id);

    int insert(SysRole role);

    int update(SysRole role);

    int deleteById(Long id);

    List<SysRole> findByRoleName(String roleName);
}