package com.advancedjavawork.config;

import com.advancedjavawork.interceptors.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/**")//拦截所有
                .excludePathPatterns("/admin",
                        "/user/login",
                        "/user/register")//排除
                .addPathPatterns();
    }
}
