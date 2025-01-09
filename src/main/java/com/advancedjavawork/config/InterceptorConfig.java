package com.advancedjavawork.config;

import com.advancedjavawork.interceptors.JWTInterceptor;
import com.advancedjavawork.service.IUserService;
import com.advancedjavawork.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Resource
    private IUserService userService;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor(userService))
                .addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/admin/**",
                        "/user/**")//排除
                .addPathPatterns();
    }
}
