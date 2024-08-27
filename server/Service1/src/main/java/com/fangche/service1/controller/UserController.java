package com.fangche.service1.controller;

import com.fangche.service1.entity.Response;
import com.fangche.service1.entity.requestParam.user.*;
import com.fangche.service1.service.UserService;
import com.fangche.service1.utils.authority.Authority;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/get/{uid}")
    public Response getUserInfo(@PathVariable("uid") String uid) {
        return userService.getUserInfo(uid); // 调用UserService的getUserInfo方法
    }
    @GetMapping({"/info/{id}", "/info"})
    public Response info(HttpServletRequest request, @PathVariable(value = "id", required = false) Long id) {
        String auth = request.getHeader("Authorization");
        return userService.info(auth, id);
    }
    @PostMapping("/register/send-code")
    public Response sendRegisterCode(@Valid @RequestBody UserRegisterSendCodeParam param) {
        return userService.sendRegisterVerifyCode(param.getAccount());
    }
    @PostMapping("/register/verify-code")
    public Response verifyRegisterCode(@Valid @RequestBody UserRegisterVerifyCodeParam param) {
        return userService.register(param.getAccount(), param.getPassword(), param.getVerify_code());
    }
    @PostMapping("/login")
    public Response login(@Valid @RequestBody UserLoginParam param, HttpServletRequest request){
        return userService.login(param.getAccount(), param.getPassword(), request);
    }
    @PostMapping("/set-avatar")
    @Authority
    public Response setAvatar(MultipartFile file,
                              HttpServletRequest request) {
        return userService.setAvatar(file, request);
    }
    @PutMapping("/update")
    @Authority
    public Response update(@RequestBody UserUpdateParam param,
                           HttpServletRequest request) {
        if (param.getGender() == null) {
            // 不修改
            param.setGender("-2");
        }
        return userService.updateUser(param.getNickname(),
                Integer.parseInt(param.getGender()),
                param.getSignature(),
                param.getIntroduction(),
                request);
    }

    @PostMapping("/reset-password/send-code")
    public Response sendResetPasswordCode(@Valid @RequestBody UserResetPswSendCodeParam param) {
        return userService.sendResetPswVerifyCode(param.getAccount());
    }
    @PostMapping("/reset-password/verify-code")
    public Response verifyResetPasswordCode(@Valid @RequestBody UserResetPswVerifyCodeParam param){
        return userService.verifyResetPsw(param.getAccount(), param.getNew_password(), param.getVerify_code());
    }

    @PutMapping("/password/update")
    @Authority
    public Response updatePassword(@Valid @RequestBody UserUpdatePasswordParam param, HttpServletRequest request) {
        return userService.updatePassword(param, request);
    }
}
