package com.advancedjavawork.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JWTUtils {
    private static final String SIGN = "wangbookmanagementsystem";

    public static String getToken(Map<String,String> map){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.MINUTE,10);

        JWTCreator.Builder builder = JWT.create();

        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(instance.getTime())//指定令牌过期时间
                .sign(Algorithm.HMAC256(SIGN));
        return token;
    }


    //验证token
    public static DecodedJWT verify(String token){
        return JWT.require(Algorithm.HMAC256(SIGN)).build().verify(token);
    }
}
