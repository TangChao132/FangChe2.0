package com.fangche.service1.controller;

import com.fangche.service1.entity.Response;
import com.fangche.service1.utils.authority.Authority;
import com.fangche.service1.utils.authority.Permission;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello1")
    @Authority(Permission.AUTHOR)
    public Response hello() {
        return new Response(200, "Hello from service1!", null);
    }
}
