package com.fangche.service1.utils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

public class UserUtil {
    /**
     *  通过request获取用户id
     * @param request 请求
     * @return  用户id
     */
    public static Long getUserIdByRequest(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        String token = (authorization.split(" "))[1];
        Claims claims = JWTUtil.getClaimsFromJwt(token);
        return (Long) claims.get("id");
    }
}
