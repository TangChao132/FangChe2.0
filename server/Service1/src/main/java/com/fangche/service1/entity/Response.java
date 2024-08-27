package com.fangche.service1.entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Response {
    public Response(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Response() {

    }

    private int code;
    private String msg;
    private Object data;
}
