package com.fangche.service1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fangche.service1.entity.Response;
import com.fangche.service1.entity.User;
import com.fangche.service1.entity.VerifyCode;
import com.fangche.service1.entity.requestParam.user.UserUpdatePasswordParam;
import com.fangche.service1.mapper.UserMapper;
import com.fangche.service1.service.UserService;
import com.fangche.service1.service.VerifyCodeService;
import com.fangche.service1.utils.*;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private VerifyCodeService verifyCodeService;

    @Override
    public Response  getUserInfo(String uid) {
        User user =  userMapper.selectById(uid);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("success");
        response.setData(user);
        return response;
    }

    /**
     * 获取用户信息
     * @param authorization 请求头中的认证信息
     * @return Response
     */
    @Override
    public Response info(String authorization, Long id) {
        if (id == null) {
            String token;
            try {
                token = (authorization.split(" "))[1];
            } catch (Exception e) {
                return null;
            }
            Claims claims = JWTUtil.getClaimsFromJwt(token);
            if (claims == null){
                return new Response(303, "token已过期,请重新登录", null);
            }
            id = (Long) claims.get("id");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        return new Response(200, "获取成功", userMapper.selectOne(wrapper));
    }

    /**
     * 注册
     * @param account 邮箱
     * @param password 密码
     * @param verifyCode 验证码
     * @return Response
     */
    @Override
    public Response register(String account, String password, String verifyCode) {
        // 验证验证码
        VerifyCode code = verifyCodeService.selectByAccount(account);
        if (code == null || code.isUsed() ||!code.getCode().equals(verifyCode)) {
            return new Response(400, "验证码错误", null);
        }
        // 获取当前时间
        Date now = new Date();

        // 计算过期时间（创建时间加上指定的分钟数） 5分钟过期
        long expiryTimeMillis = code.getCreatedAt().getTime() + (5 * 60 * 1000);
        Date expiryTime = new Date(expiryTimeMillis);

        // 如果当前时间大于或等于过期时间，那么验证码就过期了
        if (now.after(expiryTime) || now.equals(expiryTime)) {
            return new Response(401, "验证码已过期", null);
        }

        // 验证码验证通过，执行注册逻辑
        User user = new User();
        user.setAccount(account);
        // 密码进行md加盐加密
        user.setPassword(SaltMD5Util.generateSaltPassword(password));
        user.setNickname(account);
        userMapper.insert(user);

        // 设置验证码已使用
        code.setUsed(true);
        verifyCodeService.update(code);

//        System.out.println(code);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("注册成功");
        return response;
    }

    /**
     * 发送注册邮箱验证码
     * @param account 邮箱
     * @return Response
     */
    @Override
    public Response sendRegisterVerifyCode(String account) {
        // 检验账号是否被注册
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
        if (user != null) {
            return new Response(400, "账号已被注册", null);
        }
        //  生成验证码
        String code = RandomNumber.generateRandomNumber(6);

        VerifyCode verifyCode = new  VerifyCode();
        verifyCode.setAccount(account);
        verifyCode.setCode(code);
        verifyCode.setUsed(false);
        // 插入新的验证码,如果存在则更新
        verifyCodeService.insert(verifyCode);

        String subject = String.format("注册验证码: %S", code);
        int status = emailService.sendHTMLMailMessage(account, subject, EmailTemplate.getVerifyCodeTemplate(code));
        Response response = new Response();
        if (status != 0) {
            response.setCode(500);
            response.setMsg("发送验证码邮件失败");
            return response;
        }
        response.setCode(200);
        response.setMsg("发送验证码邮件成功");
        return response;
    }

    /**
     * 登录
     * @param account 邮箱
     * @param password 密码
     * @return Response
     */
    @Override
    public Response login(String account, String password, HttpServletRequest request) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
        if (user==null || !SaltMD5Util.verifySaltPassword(password, user.getPassword())) {
            return new Response(400, "账号或密码错误", null);
        }
        // 时间戳作为sessionId
        user.setSessionId(System.currentTimeMillis());
        String token = JWTUtil.generateJwtToken(user);
        // 更新sessionId
        userMapper.updateById(user);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("登录成功");
        HashMap<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("user", user);
        response.setData(data);
        return response;
    }

    /**
     *  设置头像
     * @param file 头像文件
     * @param request HttpServletRequest
     * @return Response
     */
    @Override
    public Response setAvatar(MultipartFile file, HttpServletRequest request) {

        if (file.isEmpty()) {
            return new Response(400, "未选择文件", null);
        }

        try {
            File filePath = StaticResourcesUtil.getUserAvatarPath(file.getOriginalFilename());
            file.transferTo(filePath);
        } catch (IOException e) {
            return new Response(500, "上传失败", e.getMessage());
        }

        Long uid = UserUtil.getUserIdByRequest(request);

        // 更新数据库
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", uid));
        user.setAvatar(StaticResourcesUtil.USER_AVATAR_UPLOAD_DIR + file.getOriginalFilename());
        userMapper.updateById(user);

        HashMap<String, String> data = new HashMap<>();
        data.put("id", String.valueOf(uid));
        data.put("file_name", file.getOriginalFilename());
        data.put("path", StaticResourcesUtil.USER_AVATAR_UPLOAD_DIR + file.getOriginalFilename());

        return new Response(200, "上传成功", data);
    }

    /**
     *  更新用户信息
     * @param nickname 昵称
     * @param gender 性别
     * @param signature 个性签名
     * @param introduction 简介
     * @param request HttpServletRequest
     * @return Response
     */
    @Override
    public Response updateUser(String nickname,
                               int gender,
                               String signature,
                               String introduction,
                               HttpServletRequest request) {
        if (Objects.equals(nickname, "") &&gender == -2 && Objects.equals(signature, "") && Objects.equals(introduction, "")){
            return new Response(400, "未修改任何信息", null);
        }
        Long uid = UserUtil.getUserIdByRequest(request);
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("id", uid));
        if (!Objects.equals(nickname, "")) {
            user.setNickname(nickname);
        }
        if (gender != -2) {
            user.setGender(gender);
        }
        if (!Objects.equals(signature, "")) {
            user.setSignature(signature);
        }
        if (!Objects.equals(introduction, "")) {
            user.setIntroduction(introduction);
        }
        userMapper.updateById(user);

        return new Response(200, "更新成功", user);
    }

    /**
     *  发送重置密码验证码
     * @param account 邮箱
     * @return Response
     */
    @Override
    public Response sendResetPswVerifyCode(String account) {
        // 检验账号是否被注册
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
        if (user == null) {
            return new Response(400, "账号不存在", null);
        }
        //  生成验证码
        String code = RandomNumber.generateRandomNumber(6);

        VerifyCode verifyCode = new  VerifyCode();
        verifyCode.setAccount(account);
        verifyCode.setCode(code);
        verifyCode.setUsed(false);
        // 插入新的验证码,如果存在则更新
        verifyCodeService.insert(verifyCode);

        String subject = String.format("重置密码验证码: %S", code);
        int status = emailService.sendHTMLMailMessage(account, subject, EmailTemplate.getResetPswVerifyCodeTemplate(code));
        Response response = new Response();
        if (status != 0) {
            response.setCode(500);
            response.setMsg("发送验证码邮件失败");
            return response;
        }
        response.setCode(200);
        response.setMsg("发送验证码邮件成功");
        return response;
    }

    /**
     *  验证重置密码的验证码
     * @param account 账号
     * @param password 新密码
     * @param verifyCode 验证码
     * @return Response
     */
    @Override
    public Response verifyResetPsw(String account, String password, String verifyCode) {
        // 验证验证码
        VerifyCode code = verifyCodeService.selectByAccount(account);
        if (code == null || code.isUsed() || !code.getCode().equals(verifyCode)) {
            return new Response(400, "验证码错误", null);
        }
        // 获取当前时间
        Date now = new Date();

        // 计算过期时间（创建时间加上指定的分钟数） 5分钟过期
        long expiryTimeMillis = code.getCreatedAt().getTime() + (5 * 60 * 1000);
        Date expiryTime = new Date(expiryTimeMillis);

        // 如果当前时间大于或等于过期时间，那么验证码就过期了
        if (now.after(expiryTime) || now.equals(expiryTime)) {
            return new Response(401, "验证码已过期", null);
        }

        // 验证码验证通过，执行重置密码逻辑
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("account", account));
        // 新密码进行md加盐加密
        user.setPassword(SaltMD5Util.generateSaltPassword(password));
        userMapper.updateById(user);

        // 设置验证码已使用
        code.setUsed(true);
        verifyCodeService.update(code);

//        System.out.println(code);
        Response response = new Response();
        response.setCode(200);
        response.setMsg("重置密码成功");
        return response;
    }

    @Override
    public Response updatePassword(UserUpdatePasswordParam param, HttpServletRequest request) {
        Long uid = UserUtil.getUserIdByRequest(request);
        User user = userMapper.selectById(uid);
        if (!SaltMD5Util.verifySaltPassword(param.getOld_password(), user.getPassword())) {
            return new Response(401, "原密码错误", null);
        }
        user.setPassword(SaltMD5Util.generateSaltPassword(param.getNew_password()));
        userMapper.updateById(user);
        return new Response(200, "密码修改成功", null);
    }

}
