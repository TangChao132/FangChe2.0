package com.fangche.service1.entity.requestParam.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserResetPswVerifyCodeParam {
    @NotBlank
    private String account;
    @NotBlank
    private String verify_code;
    @NotBlank
    private String new_password;
}
