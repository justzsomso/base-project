package com.base.admin.mapper;

import com.base.admin.entity.SysUser;
import java.util.List;

public interface SysUserMapper {

    List<SysUser> findAll();

    SysUser findById(Long id);

    int insert(SysUser user);

    int update(SysUser user);

    int deleteById(Long id);
}