package com.base.admin.controller;

import com.base.admin.entity.SysUser;
import com.base.admin.service.SysUserService;
import com.base.admin.util.PageResult;
import com.base.admin.dto.SysUserQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    // 根据条件分页查询用户
    @GetMapping("/")
    public PageResult<SysUser> getAllUsers(SysUserQueryDTO userQuery) {
        return sysUserService.findByCondition(userQuery);
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public SysUser getUserById(@PathVariable Long id) {
        return sysUserService.findById(id);
    }

    // 创建用户
    @PostMapping("/")
    public boolean createUser(@RequestBody SysUser user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        return sysUserService.save(user);
    }

    // 更新用户
    @PutMapping("/")
    public boolean updateUser(@RequestBody SysUser user) {
        user.setUpdateTime(new Date());
        return sysUserService.update(user);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return sysUserService.deleteById(id);
    }
}