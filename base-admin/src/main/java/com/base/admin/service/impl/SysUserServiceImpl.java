package com.base.admin.service.impl;

import com.base.admin.entity.SysUser;
import com.base.admin.mapper.SysUserMapper;
import com.base.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    
    @Autowired
    private SysUserMapper sysUserMapper;
    
    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
    

    /**
     * 根据ID查询用户
     * @param id 用户ID
     * @return 用户信息
     */
    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }

    /**
     * 保存用户信息
     * @param user 用户对象
     * @return 是否保存成功
     */
    @Override
    public boolean save(SysUser user) {
        return sysUserMapper.insert(user) > 0;
    }
    
    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 是否更新成功
     */
    @Override
    public boolean update(SysUser user) {
        return sysUserMapper.update(user) > 0;
    }
    
    /**
     * 根据ID删除用户
     * @param id 用户ID
     * @return 是否删除成功
     */
    @Override
    public boolean deleteById(Long id) {
        return sysUserMapper.deleteById(id) > 0;
    }
}