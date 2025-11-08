package com.base.admin.controller;

import com.base.admin.entity.SysRole;
import com.base.admin.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    // 查询所有角色
    @GetMapping("/")
    public List<SysRole> getAllRoles() {
        return sysRoleService.findAll();
    }

    // 根据ID查询角色
    @GetMapping("/{id}")
    public SysRole getRoleById(@PathVariable Long id) {
        return sysRoleService.findById(id);
    }

    // 根据角色名称查询角色
    @GetMapping("/search")
    public List<SysRole> getRoleByName(@RequestParam String roleName) {
        return sysRoleService.findByRoleName(roleName);
    }

    // 创建角色
    @PostMapping("/")
    public boolean createRole(@RequestBody SysRole role) {
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        return sysRoleService.save(role);
    }

    // 更新角色
    @PutMapping("/")
    public boolean updateRole(@RequestBody SysRole role) {
        role.setUpdateTime(new Date());
        return sysRoleService.update(role);
    }

    // 删除角色
    @DeleteMapping("/{id}")
    public boolean deleteRole(@PathVariable Long id) {
        return sysRoleService.deleteById(id);
    }
}