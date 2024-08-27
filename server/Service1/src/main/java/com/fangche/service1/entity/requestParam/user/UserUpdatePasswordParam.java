package com.fangche.service1.entity.requestParam.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdatePasswordParam {
    @NotBlank
    private String old_password; // 旧密码
    @NotBlank
    private String new_password; // 新密码
}
