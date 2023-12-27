package com.wxapp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: wxapp
 * @description:
 * @author: WONGhaiLONG
 * @create: 2023-12-12 20:54
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.wxapp.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
