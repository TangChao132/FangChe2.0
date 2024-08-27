package com.fangche.service1.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.tangzc.mpe.autotable.annotation.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
@Setter
@Table
public class VerifyCode {
    /**
     * 验证码
     */
    @TableId("account")
    private String account;
    private String code;
    @TableField(fill = FieldFill.INSERT)
    private Date createdAt;
    private boolean used;
}
