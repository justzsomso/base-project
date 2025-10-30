package com.base.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxx.system.dto.SysUserDTO;
import com.xxx.system.entity.SysUser;
import com.xxx.system.vo.SysUserVO;

public interface SysUserService extends IService<SysUser> {
    SysUserVO getById(Long id);

    IPage<SysUserVO> page(Page<SysUserVO> page, String username);

    void save(SysUserDTO userDTO);

    void update(SysUserDTO userDTO);

    void delete(Long id);
}