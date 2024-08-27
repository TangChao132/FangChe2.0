package com.fangche.service1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fangche.service1.entity.VerifyCode;
import com.fangche.service1.mapper.VerifyCodeMapper;
import com.fangche.service1.service.VerifyCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Autowired
    private VerifyCodeMapper verifyCodeMapper;

    @Override
    public void insert(VerifyCode verifyCode) {
        // 调用Mapper检查验证码数据是否存在
        QueryWrapper<VerifyCode> wrapper = new QueryWrapper<>();
        wrapper.eq("account", verifyCode.getAccount());
        int count = Math.toIntExact(verifyCodeMapper.selectCount(wrapper));

        if (count > 0) {
            // 如果存在，则更新
            verifyCode.setCreatedAt(new Date());
            verifyCodeMapper.updateById(verifyCode);
        } else {
            // 如果不存在，则插入
            verifyCodeMapper.insert(verifyCode);
        }
    }
    @Override
    public VerifyCode selectByAccount(String account) {
        return  verifyCodeMapper.selectOne(new QueryWrapper<VerifyCode>().eq("account", account));
    }
    @Override
    public void update(VerifyCode verifyCode) {
        verifyCodeMapper.updateById(verifyCode);
    }
}
