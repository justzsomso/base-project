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
    
    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.findAll();
    }
    
    @Override
    public SysUser findById(Long id) {
        return sysUserMapper.findById(id);
    }
    
    @Override
    public boolean save(SysUser user) {
        return sysUserMapper.insert(user) > 0;
    }
    
    @Override
    public boolean update(SysUser user) {
        return sysUserMapper.update(user) > 0;
    }
    
    @Override
    public boolean deleteById(Long id) {
        return sysUserMapper.deleteById(id) > 0;
    }
}