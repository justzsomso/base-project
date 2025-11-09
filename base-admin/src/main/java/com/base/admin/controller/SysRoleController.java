package com.base.admin.controller;

import com.base.admin.entity.SysRole;
import com.base.admin.service.SysRoleService;
import com.base.admin.util.PageResult;
import com.base.admin.util.ApiResult;
import com.base.admin.dto.SysRoleQueryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    // 根据条件分页查询角色
    @GetMapping("/")
    public ApiResult<PageResult<SysRole>> getAllRoles(SysRoleQueryDTO roleQuery) {
        PageResult<SysRole> pageResult = sysRoleService.findByCondition(roleQuery);
        return ApiResult.success(pageResult);
    }

    // 根据ID查询角色
    @GetMapping("/{id}")
    public ApiResult<SysRole> getRoleById(@PathVariable Long id) {
        SysRole role = sysRoleService.findById(id);
        return ApiResult.success(role);
    }

    // 根据角色名称查询角色
    @GetMapping("/search")
    public ApiResult<List<SysRole>> getRoleByName(@RequestParam String roleName) {
        List<SysRole> roles = sysRoleService.findByRoleName(roleName);
        return ApiResult.success(roles);
    }

    // 创建角色
    @PostMapping("/")
    public ApiResult<Boolean> createRole(@RequestBody SysRole role) {
        try {
            role.setCreateTime(new Date());
            role.setUpdateTime(new Date());
            boolean success = sysRoleService.save(role);
            if (success) {
                return ApiResult.success(true, "角色创建成功");
            } else {
                return ApiResult.failed("角色创建失败");
            }
        } catch (IllegalArgumentException e) {
            return ApiResult.failed(400, e.getMessage());
        } catch (Exception e) {
            return ApiResult.failed(500, "系统错误：" + e.getMessage());
        }
    }

    // 更新角色
    @PutMapping("/")
    public ApiResult<Boolean> updateRole(@RequestBody SysRole role) {
        role.setUpdateTime(new Date());
        boolean success = sysRoleService.update(role);
        if (success) {
            return ApiResult.success(true, "角色更新成功");
        } else {
            return ApiResult.failed("角色更新失败");
        }
    }

    // 删除角色
    @DeleteMapping("/{id}")
    public ApiResult<Boolean> deleteRole(@PathVariable Long id) {
        boolean success = sysRoleService.deleteById(id);
        if (success) {
            return ApiResult.success(true, "角色删除成功");
        } else {
            return ApiResult.failed("角色删除失败");
        }
    }
}