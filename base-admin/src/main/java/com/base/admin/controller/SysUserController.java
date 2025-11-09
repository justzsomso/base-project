package com.base.admin.controller;

import com.base.admin.dto.SysUserQueryDTO;
import com.base.admin.entity.SysUser;
import com.base.admin.service.SysUserService;
import com.base.admin.util.PageResult;
import com.base.admin.util.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    // 根据条件分页查询用户
    @GetMapping("/")
    public ApiResult<PageResult<SysUser>> getAllUsers(SysUserQueryDTO userQuery) {
        PageResult<SysUser> pageResult = sysUserService.findByCondition(userQuery);
        return ApiResult.success(pageResult);
    }

    // 根据ID查询用户
    @GetMapping("/{id}")
    public ApiResult<SysUser> getUserById(@PathVariable Long id) {
        SysUser user = sysUserService.findById(id);
        return ApiResult.success(user);
    }

    // 创建用户
    @PostMapping("/")
    public ApiResult<Boolean> createUser(@RequestBody SysUser user) {
        try {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            boolean success = sysUserService.save(user);
            if (success) {
                return ApiResult.success(true, "用户创建成功");
            } else {
                return ApiResult.failed("用户创建失败");
            }
        } catch (IllegalArgumentException e) {
            return ApiResult.failed(400, e.getMessage());
        } catch (Exception e) {
            return ApiResult.failed(500, "系统错误：" + e.getMessage());
        }
    }

    // 更新用户
    @PutMapping("/")
    public ApiResult<Boolean> updateUser(@RequestBody SysUser user) {
        try {
            user.setUpdateTime(new Date());
            boolean success = sysUserService.update(user);
            if (success) {
                return ApiResult.success(true, "用户更新成功");
            } else {
                return ApiResult.failed("用户更新失败");
            }
        } catch (IllegalArgumentException e) {
            return ApiResult.failed(400, e.getMessage());
        } catch (Exception e) {
            return ApiResult.failed(500, "系统错误：" + e.getMessage());
        }
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> deleteUser(@PathVariable Long id) {
        boolean success = sysUserService.deleteById(id);
        if (success) {
            return ApiResult.success(true, "用户删除成功");
        } else {
            return ApiResult.failed("用户删除失败");
        }
    }
}