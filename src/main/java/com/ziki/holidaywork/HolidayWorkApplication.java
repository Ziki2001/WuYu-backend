package com.ziki.holidaywork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@MapperScan("com.ziki.holidaywork.dao")
@PropertySource("classpath:config.properties")

public class HolidayWorkApplication {
    public static void main(String[] args) {
        SpringApplication.run(HolidayWorkApplication.class, args);
    }

}
