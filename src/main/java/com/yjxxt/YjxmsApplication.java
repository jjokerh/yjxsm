package com.yjxxt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yjxxt.mapper")
public class YjxmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YjxmsApplication.class, args);
    }

}
