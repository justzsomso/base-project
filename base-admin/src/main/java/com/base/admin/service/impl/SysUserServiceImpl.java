package com.base.admin.service.impl;

import com.base.admin.dto.PageResult;
import com.base.admin.dto.UserQueryDTO;
import com.base.admin.entity.SysUser;
import com.base.admin.mapper.SysUserMapper;
import com.base.admin.service.SysUserService;
import com.base.admin.util.PasswordUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        // 检查用户名是否已存在
        SysUser existingUser = sysUserMapper.findByUsername(user.getUsername());
        if (existingUser != null) {
            throw new IllegalArgumentException("已存在该用户名");
        }
        
        // 对密码进行加密处理
        String rawPassword = user.getPassword();
        if (rawPassword != null && !rawPassword.isEmpty()) {
            // 检查密码强度
            if (!PasswordUtil.isValidPassword(rawPassword)) {
                throw new IllegalArgumentException("密码强度不符合要求，密码必须至少8位，包含数字、小写字母、大写字母和特殊字符中的至少3种");
            }
            user.setPassword(PasswordUtil.encode(rawPassword));
        }
        return sysUserMapper.insert(user) > 0;
    }
    
    @Override
    public boolean update(SysUser user) {
        // 获取数据库中的用户信息
        SysUser dbUser = sysUserMapper.findById(user.getId());
        if (dbUser == null) {
            throw new IllegalArgumentException("用户不存在");
        }
        
        // 检查密码是否有变更
        String rawPassword = user.getPassword();
        if (rawPassword != null && !rawPassword.isEmpty()) {
            // 如果密码有变更，则需要校验密码强度并加密
            if (!PasswordUtil.isValidPassword(rawPassword)) {
                throw new IllegalArgumentException("密码强度不符合要求，密码必须至少8位，包含数字、小写字母、大写字母和特殊字符中的至少3种");
            }
            
            // 检查新密码是否与旧密码一致
            if (PasswordUtil.matches(rawPassword, dbUser.getPassword())) {
                throw new IllegalArgumentException("新密码不能与旧密码相同");
            }
            
            // 对新密码进行加密
            user.setPassword(PasswordUtil.encode(rawPassword));
        } else {
            // 如果没有提供新密码，则保留原密码
            user.setPassword(dbUser.getPassword());
        }
        
        return sysUserMapper.update(user) > 0;
    }
    
    @Override
    public boolean deleteById(Long id) {
        return sysUserMapper.deleteById(id) > 0;
    }
    
    @Override
    public PageResult<SysUser> findPage(UserQueryDTO queryDTO) {
        // 使用PageHelper进行分页
        PageHelper.startPage(queryDTO.getPageNum(), queryDTO.getPageSize());
        List<SysUser> users = sysUserMapper.findPage(queryDTO);
        PageInfo<SysUser> pageInfo = new PageInfo<>(users);
        
        // 封装分页结果
        return new PageResult<>(users, pageInfo.getTotal(), queryDTO.getPageSize(), queryDTO.getPageNum());
    }
}