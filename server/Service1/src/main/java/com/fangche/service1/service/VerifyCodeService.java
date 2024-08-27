package com.fangche.service1.service;

import com.fangche.service1.entity.VerifyCode;

public interface VerifyCodeService {
    void insert(VerifyCode verifyCode);
    VerifyCode selectByAccount(String account);
    void update(VerifyCode verifyCode);
}
