package com.fangche.service1.entity.requestParam.user;

import lombok.Data;

@Data
public class UserUpdateParam {
    private String nickname;
    private String gender;
    private String signature;
    private String introduction;
}
