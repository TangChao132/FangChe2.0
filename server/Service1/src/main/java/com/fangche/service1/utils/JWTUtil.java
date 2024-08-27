package com.fangche.service1.utils;

import com.fangche.service1.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class JWTUtil {

        //加密算法
        private final static SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

        //私钥
        private final static String secret = "fangche666";

        // 过期时间（单位秒）/ 3天
        private final static Long access_token_expiration = 259200L;

        //jwt签发者
        private final static String jwt_iss = "fangche";

        //jwt所有人
        private final static String subject = "fangche";

    /**
     * 创建jwt
     * @return
     *      返回生成的jwt token
     */
    public static String generateJwtToken(User user){

        // 头部 map / Jwt的头部承载，第一部分
        // 可不设置 默认格式是{"alg":"HS256"}
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");


        //载荷 map / Jwt的载荷，第二部分
        Map<String,Object> claims = new HashMap<String,Object>();

        //私有声明 / 自定义数据，根据业务需要添加
        claims.put("id", user.getId());
        claims.put("account", user.getAccount());
        claims.put("password", user.getPassword());
        claims.put("nickname", user.getNickname());
        claims.put("role", user.getRole());
        claims.put("created_at", user.getCreatedAt());
        claims.put("session_id", user.getSessionId());

        //标准中注册的声明 (建议但不强制使用)
        //一旦写标准声明赋值之后，就会覆盖了那些标准的声明
        claims.put("iss", jwt_iss);
            /*	iss: jwt签发者
                sub: jwt所面向的用户
                aud: 接收jwt的一方
                exp: jwt的过期时间，这个过期时间必须要大于签发时间
                nbf: 定义在什么时间之前，该jwt都是不可用的.
                iat: jwt的签发时间
                jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
            */


        //下面就是在为payload添加各种标准声明和私有声明了
        return Jwts.builder() // 这里其实就是new一个JwtBuilder，设置jwt的body
                .setHeader(map)         // 头部信息
                .setClaims(claims)      // 载荷信息
                .setId(UUID.randomUUID().toString()) // 设置jti(JWT ID)：是JWT的唯一标识，从而回避重放攻击。
                .setIssuedAt(new Date())       // 设置iat: jwt的签发时间
                .setExpiration(new Date(System.currentTimeMillis() + access_token_expiration * 1000)) // 设置exp：jwt过期时间
                .setSubject(subject)    //设置sub：代表这个jwt所面向的用户，所有人
                .signWith(SIGNATURE_ALGORITHM, secret)//设置签名：通过签名算法和秘钥生成签名
                .compact(); // 开始压缩为xxxxx.yyyyy.zzzzz 格式的jwt token
    }

    /**
     *  从jwt中获取 载荷 信息
     */
    public static Claims getClaimsFromJwt(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            System.out.println("token过期");
            return claims;
        }
        return claims;
    }
}
