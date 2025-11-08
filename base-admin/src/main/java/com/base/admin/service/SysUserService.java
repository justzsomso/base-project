package com.base.admin.service;

import com.base.admin.dto.PageResult;
import com.base.admin.dto.UserQueryDTO;
import com.base.admin.entity.SysUser;

import java.util.List;

public interface SysUserService {
    /**
     * 根据条件分页查询用户
     * @param userQuery 查询条件
     * @return 分页结果
     */
    PageResult<SysUser> findByCondition(SysUserQueryDTO userQuery);

    SysUser findById(Long id);

    boolean save(SysUser user);

    boolean update(SysUser user);

    boolean deleteById(Long id);
}