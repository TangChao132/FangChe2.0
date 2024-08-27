package com.fangche.service1.entity.requestParam.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class UserLoginParam {
    @NotBlank(message = "账号不能为空")
    private String account;
    @NotNull(message = "密码不能为空")
    private String password;
}
