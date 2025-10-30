package com.base.system.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SysUserDTO {
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    private String password; // 新增时必填，更新时可选

    private String realName;

    private String phone;

    private String email;

    private Integer status;
}