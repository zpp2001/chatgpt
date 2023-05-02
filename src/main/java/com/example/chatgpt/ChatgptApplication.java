package com.example.chatgpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.chatgpt.Mapper")
@SpringBootApplication
public class ChatgptApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatgptApplication.class, args);
    }

}
