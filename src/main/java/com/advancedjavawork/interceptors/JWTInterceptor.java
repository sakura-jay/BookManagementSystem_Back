package com.advancedjavawork.interceptors;

import com.advancedjavawork.service.IUserService;
import com.advancedjavawork.utils.JWTUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;
@Component
public class JWTInterceptor implements HandlerInterceptor {
    private final IUserService userService;

    public JWTInterceptor(IUserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String,Object> map = new HashMap<>();
        String token = request.getHeader("token");
        if (token==null){//请求头为空
            token = request.getParameter("token");
        }
//        System.out.println(token);
        try {
            JWTUtils.verify(token);
            return true;
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期");
//            UserController userController = new UserController();
            DecodedJWT jwt = JWT.decode(token);
            Claim userName = jwt.getClaim("userName");
//            System.out.println(userName.asString());
            userService.setOnlineStatus(userName.asString(),0);
//            userController.setUserOnlineStatus(userName.asString(),0);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","无效token");
        }
        map.put("state",false);
        String json = new ObjectMapper().writeValueAsString(map);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().println(json);//返回错误信息
        return false;
    }
}
