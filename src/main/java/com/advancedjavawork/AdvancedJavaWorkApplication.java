package com.advancedjavawork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.advancedjavawork.mapper")
public class AdvancedJavaWorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedJavaWorkApplication.class, args);
    }

}
