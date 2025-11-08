package com.base.admin.controller;

import com.base.admin.entity.SysUser;
import com.base.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, Object> createUser(@RequestBody SysUser user) {
        Map<String, Object> result = new HashMap<>();
        try {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            boolean success = sysUserService.save(user);
            result.put("success", success);
            result.put("message", success ? "用户创建成功" : "用户创建失败");
        } catch (IllegalArgumentException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "系统错误：" + e.getMessage());
        }
        return result;
    }

    // 更新用户
    @PutMapping("/")
    public Map<String, Object> updateUser(@RequestBody SysUser user) {
        Map<String, Object> result = new HashMap<>();
        try {
            user.setUpdateTime(new Date());
            boolean success = sysUserService.update(user);
            result.put("success", success);
            result.put("message", success ? "用户更新成功" : "用户更新失败");
        } catch (IllegalArgumentException e) {
            result.put("success", false);
            result.put("message", e.getMessage());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "系统错误：" + e.getMessage());
        }
        return result;
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public boolean deleteUser(@PathVariable Long id) {
        return sysUserService.deleteById(id);
    }
}