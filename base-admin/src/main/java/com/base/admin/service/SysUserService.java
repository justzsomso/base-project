package com.base.admin.service;

import com.base.admin.entity.SysUser;
import java.util.List;

public interface SysUserService {

    List<SysUser> findAll();

    SysUser findById(Long id);

    boolean save(SysUser user);

    boolean update(SysUser user);

    boolean deleteById(Long id);
}