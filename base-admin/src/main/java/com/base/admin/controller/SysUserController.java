package com.base.admin.controller;

import com.base.admin.entity.SysUser;
import com.base.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    // 查询所有用户
    @GetMapping("/")
    public List<SysUser> getAllUsers() {
        return sysUserService.findAll();
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public SysUser getUserById(@PathVariable Long id) {
        return sysUserService.findById(id);
    }

    // 创建用户
    @PostMapping("/")
    public boolean createUser(@RequestBody SysUser user) {
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return sysUserService.save(user);
    }

    // 更新用户
    @PutMapping("/")
    public boolean updateUser(@RequestBody SysUser user) {
        user.setUpdateTime(LocalDateTime.now());
        return sysUserService.update(user);
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return sysUserService.deleteById(id);
    }
}