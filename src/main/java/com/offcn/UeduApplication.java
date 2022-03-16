package com.offcn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.offcn.mapper")
public class UeduApplication {

    public static void main(String[] args) {

        SpringApplication.run(UeduApplication.class, args);
    }

}
