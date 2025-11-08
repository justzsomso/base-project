package com.base.admin.mapper;

import com.base.admin.entity.SysUser;
import com.base.admin.dto.SysUserQueryDTO;
import java.util.List;

public interface SysUserMapper {
    /**
     * 根据条件查询用户列表
     * @param userQuery 查询条件
     * @return 用户列表
     */
    List<SysUser> findByCondition(SysUserQueryDTO userQuery);

    SysUser findById(Long id);

    int insert(SysUser user);

    int update(SysUser user);

    int deleteById(Long id);


}