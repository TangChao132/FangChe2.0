package com.fangche.service1.entity.requestParam.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterSendCodeParam {
    @NotBlank
    private String account;
}
