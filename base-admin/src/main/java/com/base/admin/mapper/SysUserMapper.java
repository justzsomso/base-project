package com.base.admin.mapper;

import com.base.admin.dto.UserQueryDTO;
import com.base.admin.entity.SysUser;
import java.util.List;

public interface SysUserMapper {

    List<SysUser> findAll();

    SysUser findById(Long id);

    int insert(SysUser user);

    int update(SysUser user);

    int deleteById(Long id);
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户信息
     */
    SysUser findByUsername(String username);
    
    /**
     * 分页查询用户
     * @param queryDTO 查询条件
     * @return 用户列表
     */
    List<SysUser> findPage(UserQueryDTO queryDTO);
}