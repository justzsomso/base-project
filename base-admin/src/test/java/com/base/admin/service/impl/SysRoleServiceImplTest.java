package com.base.admin.service.impl;

import com.base.admin.entity.SysRole;
import com.base.admin.mapper.SysRoleMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SysRoleServiceImplTest {

    @InjectMocks
    private SysRoleServiceImpl sysRoleService;

    @Mock
    private SysRoleMapper sysRoleMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRole_Success() {
        // 准备测试数据
        SysRole role = new SysRole();
        role.setRoleName("ADMIN");
        role.setRoleCode("ROLE_ADMIN");
        role.setDescription("Administrator Role");
        
        // 模拟mapper方法返回
        when(sysRoleMapper.findByRoleNameExact("ADMIN")).thenReturn(null);
        when(sysRoleMapper.insert(role)).thenReturn(1);
        
        // 执行测试
        boolean result = sysRoleService.save(role);
        
        // 验证结果
        assertTrue(result);
        verify(sysRoleMapper, times(1)).findByRoleNameExact("ADMIN");
        verify(sysRoleMapper, times(1)).insert(role);
    }

    @Test
    public void testSaveRole_DuplicateName() {
        // 准备测试数据
        SysRole role = new SysRole();
        role.setRoleName("ADMIN");
        role.setRoleCode("ROLE_ADMIN");
        role.setDescription("Administrator Role");
        
        // 模拟已存在同名角色
        SysRole existingRole = new SysRole();
        existingRole.setId(1L);
        existingRole.setRoleName("ADMIN");
        
        when(sysRoleMapper.findByRoleNameExact("ADMIN")).thenReturn(existingRole);
        
        // 执行测试并验证异常
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            sysRoleService.save(role);
        });
        
        assertEquals("已存在该角色名称", exception.getMessage());
        verify(sysRoleMapper, times(1)).findByRoleNameExact("ADMIN");
        verify(sysRoleMapper, never()).insert(role);
    }
}