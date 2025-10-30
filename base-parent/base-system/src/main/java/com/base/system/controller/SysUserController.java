package com.base.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.base.common.model.R;
import com.base.system.dto.SysUserDTO;
import com.base.system.service.SysUserService;
import com.base.system.vo.SysUserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/system/user")
@Tag(name = "用户管理", description = "人员信息CRUD操作")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询用户")
    public R<SysUserVO> getById(@PathVariable Long id) {
        return R.ok(userService.getById(id));
    }

    @GetMapping("/page")
    @Operation(summary = "分页查询用户")
    public R<IPage<SysUserVO>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10") Integer pageSize,
            String username
    ) {
        Page<SysUserVO> page = new Page<>(pageNum, pageSize);
        return R.ok(userService.page(page, username));
    }

    @PostMapping
    @Operation(summary = "新增用户")
    public R<Void> save(@Valid @RequestBody SysUserDTO userDTO) {
        userService.save(userDTO);
        return R.ok();
    }

    @PutMapping
    @Operation(summary = "修改用户")
    public R<Void> update(@Valid @RequestBody SysUserDTO userDTO) {
        userService.update(userDTO);
        return R.ok();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public R<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return R.ok();
    }
}