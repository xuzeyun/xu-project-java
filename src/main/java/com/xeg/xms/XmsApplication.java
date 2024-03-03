package com.xeg.xms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xeg.xms.mapper")
public class XmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(XmsApplication.class, args);
    }

}
