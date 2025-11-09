package com.base.admin.controller;

import com.base.admin.dto.SysRoleQueryDTO;
import com.base.admin.entity.SysRole;
import com.base.admin.service.SysRoleService;
import com.base.admin.util.ApiResult;
import com.base.admin.util.PageResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SysRoleControllerTest {

    private MockMvc mockMvc;

    @Mock
    private SysRoleService sysRoleService;

    @InjectMocks
    private SysRoleController sysRoleController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sysRoleController).build();
    }

    @Test
    public void testGetAllRoles() throws Exception {
        // 准备测试数据
        SysRoleQueryDTO roleQuery = new SysRoleQueryDTO();
        roleQuery.setPageNum(1);
        roleQuery.setPageSize(10);
        
        PageResult<SysRole> pageResult = new PageResult<>();
        pageResult.setPageNum(1);
        pageResult.setPageSize(10);
        pageResult.setTotal(0);
        pageResult.setTotalPages(0);
        
        // 模拟服务层返回
        when(sysRoleService.findByCondition(any(SysRoleQueryDTO.class))).thenReturn(pageResult);
        
        // 执行测试
        mockMvc.perform(get("/role/")
                .param("pageNum", "1")
                .param("pageSize", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.pageNum").value(1));
    }

    @Test
    public void testGetRoleById() throws Exception {
        // 准备测试数据
        SysRole role = new SysRole();
        role.setId(1L);
        role.setRoleName("ADMIN");
        role.setRoleCode("ROLE_ADMIN");
        role.setDescription("Administrator role");
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        
        // 模拟服务层返回
        when(sysRoleService.findById(1L)).thenReturn(role);
        
        // 执行测试
        mockMvc.perform(get("/role/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.roleName").value("ADMIN"));
    }

    @Test
    public void testGetRoleByName() throws Exception {
        // 准备测试数据
        List<SysRole> roles = new ArrayList<>();
        SysRole role = new SysRole();
        role.setId(1L);
        role.setRoleName("ADMIN");
        role.setRoleCode("ROLE_ADMIN");
        roles.add(role);
        
        // 模拟服务层返回
        when(sysRoleService.findByRoleName("ADMIN")).thenReturn(roles);
        
        // 执行测试
        mockMvc.perform(get("/role/search")
                .param("roleName", "ADMIN")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].roleName").value("ADMIN"));
    }

    @Test
    public void testCreateRole_Success() throws Exception {
        // 准备测试数据
        String roleJson = "{ \"roleName\": \"ADMIN\", \"roleCode\": \"ROLE_ADMIN\", \"description\": \"Administrator role\" }";
        
        // 模拟服务层返回
        when(sysRoleService.save(any(SysRole.class))).thenReturn(true);
        
        // 执行测试
        mockMvc.perform(post("/role/")
                .content(roleJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("角色创建成功"));
    }
    
    @Test
    public void testCreateRole_DuplicateName() throws Exception {
        // 准备测试数据
        String roleJson = "{ \"roleName\": \"ADMIN\", \"roleCode\": \"ROLE_ADMIN\", \"description\": \"Administrator role\" }";
        
        // 模拟服务层抛出异常
        when(sysRoleService.save(any(SysRole.class))).thenThrow(new IllegalArgumentException("已存在该角色名称"));
        
        // 执行测试
        mockMvc.perform(post("/role/")
                .content(roleJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.code").value(400))
                .andExpect(jsonPath("$.message").value("已存在该角色名称"));
    }

    @Test
    public void testUpdateRole_Success() throws Exception {
        // 准备测试数据
        String roleJson = "{ \"id\": 1, \"roleName\": \"ADMIN\", \"roleCode\": \"ROLE_ADMIN\", \"description\": \"Administrator role\" }";
        
        // 模拟服务层返回
        when(sysRoleService.update(any(SysRole.class))).thenReturn(true);
        
        // 执行测试
        mockMvc.perform(put("/role/")
                .content(roleJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("角色更新成功"));
    }

    @Test
    public void testDeleteRole_Success() throws Exception {
        // 模拟服务层返回
        when(sysRoleService.deleteById(1L)).thenReturn(true);
        
        // 执行测试
        mockMvc.perform(delete("/role/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.message").value("角色删除成功"));
    }

    @Test
    public void testDeleteRole_Failed() throws Exception {
        // 模拟服务层返回
        when(sysRoleService.deleteById(1L)).thenReturn(false);
        
        // 执行测试
        mockMvc.perform(delete("/role/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("角色删除失败"));
    }
}