package com.fangche.service2.Pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Result {
    private Integer code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(200, "success", null);
    }

    //查询 成功响应
    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    //失败响应
    public static Result error(String msg) {
        return new Result(400, msg, null);
    }


}
