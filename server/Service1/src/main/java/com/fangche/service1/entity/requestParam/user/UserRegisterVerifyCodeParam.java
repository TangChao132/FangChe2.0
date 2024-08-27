package com.fangche.service1.entity.requestParam.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterVerifyCodeParam {
    @NotBlank
    private String account;
    @NotBlank
    private String verify_code;
    @NotBlank
    private String password;

}
