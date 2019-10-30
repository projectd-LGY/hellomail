package com.firstmail.hellomail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling//开启基于注解的定时任务
@SpringBootApplication//启动类
public class HellomailApplication {

    public static void main(String[] args) {
        SpringApplication.run(HellomailApplication.class, args);
    }


}
